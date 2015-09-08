package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


	

public class ReadLog {
	
	  private static final String DB_DRIVER = "org.h2.Driver";
	  private static final String DB_CONNECTION = "jdbc:h2:~/mIRCAnalysis";
	  private static final String DB_USER = "sa";
	  private static final String DB_PASSWORD = "";
	
	private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
	
	private static void insertWithStatement() throws SQLException {
		// check if db exists, create if not
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
//			create table to hold quotes
			stmt.execute("CREATE TABLE IF NOT EXISTS MIRCANALYSIS_QUOTES(id int primary key auto_increment, nick varchar(255), date datetime, quote varchar(1000))");
//			create table to hold words
			stmt.execute("CREATE TABLE IF NOT EXISTS MIRCANALYSIS_WORDS(id int primary key auto_increment, word varchar(255))");
			stmt.close();
			connection.commit();
		} catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

	}

		public static ArrayList<QuoteLine> load(String file) {
			try {
				insertWithStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ArrayList<QuoteLine> quoteLines = new ArrayList<QuoteLine>();
			try {
				//File file = new File("\\\\192.168.0.5\\c\\mirc\\logs\\#dagodz.QuakeNet.log");
				//File file = new File("c:\\temp\\test.txt");
				// incFile is now dynamically passed to enable differing logs to be parsed
				File incFile = new File(file);
				FileReader fileReader = new FileReader(incFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
//				ArrayList<String> time = new ArrayList<String>();
								
				
				// to handle change of date
				
				int month = 1;
				int year = 2015;
				int day = 1;
				
				//0 = time, 1 = person, 2 = line
				Connection connection = getDBConnection();
				while ((line = bufferedReader.readLine()) != null) {
					
					//db
					PreparedStatement stmt = null;
					// end db
					
					stringBuffer.append(line);
					stringBuffer.append("\n");
					String firstChar = new String();
					if (line.length() > 0) {
						if (line.length() > 14){
							if (line.substring(0,13).equals("Session Start") || line.substring(0,12).equals("Session Time")) {
							// updating the day to reflect new session
								//calculate where the date starts
								int dateIndex = line.indexOf(":");
							// grab month
							try {
								SimpleDateFormat formatter = new SimpleDateFormat("MMM");
								String monthInString = line.substring(dateIndex+6,dateIndex+9);
								Date date = formatter.parse(monthInString);
								SimpleDateFormat formatter1 = new SimpleDateFormat("M");
								month = Integer.parseInt(formatter1.format(date));
							}
							catch (ParseException e)
				            {
				                e.printStackTrace();
				            }
							// grab day
							day = Integer.parseInt(line.substring(dateIndex+10,dateIndex+12));
							year = Integer.parseInt(line.substring(dateIndex+22,dateIndex+26));
							}
						}
						
						if (line.length() > 0) { firstChar = line.substring(0,1); 
							if (firstChar.contains("[")) {
								if (line.contains(">") && line.substring(8,9).contains("<")) {
																		
									int hour = Integer.parseInt(line.substring(1,3));
									int minute = Integer.parseInt(line.substring(4,6));
									LocalDateTime quoteTime = LocalDateTime.of(year, month, day, hour, minute);
									
									int endNick = line.indexOf(">");
									
									// new class testing
									
									QuoteLine quoteLine = new QuoteLine();
									quoteLine.add(quoteTime, line.substring(9,endNick), line.substring(endNick + 2, line.length()));
									quoteLines.add(quoteLine);
									
									//db
									String nickname = line.substring(9,endNick);
									String quoteText = line.substring(endNick + 2, line.length());
									try {
										String insertQuery = "INSERT INTO MIRCANALYSIS_QUOTES (date, quote, nick) VALUES (?,?,?)";
										stmt = connection.prepareStatement(insertQuery);
										java.sql.Timestamp timestamp = Timestamp.valueOf(quoteTime);
										stmt.setTimestamp(1, timestamp);
										stmt.setString(2, quoteText);
										stmt.setString(3, nickname);
										stmt.executeUpdate();
										stmt.close();
										connection.commit();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String[] excludedWords = new String[]{"like", "I'm", "it's", "just", "I", "a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"};
									String [] arr = quoteText.split(" ");
									String insertQuery = "INSERT INTO MIRCANALYSIS_WORDS (word) VALUES (?)";
									try {								
										for (String ss : arr) {
											if (ss.substring(ss.length()-1,ss.length()).equals(",")) {
												ss = ss.substring(0,ss.length()-1);
											}
											if (ss.length()>2 && (!Arrays.asList(excludedWords).contains(ss) && !ss.substring(0,1).equals("!"))){
												stmt = connection.prepareStatement(insertQuery);
												stmt.setString(1,ss);
												stmt.executeUpdate();
												stmt.close();
												connection.commit();
											}
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
											
									}

								}
							
							}
						}
					
					}
				
				fileReader.close();
					
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("ReadLog size: " + quoteLines.size());
			return quoteLines;
		}
	}
		