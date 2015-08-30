package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import model.*;


	public class ReadLog {

		public static ArrayList<QuoteLine> Load() {
			ArrayList<QuoteLine> quoteLines = new ArrayList<QuoteLine>();
			try {
				//File file = new File("\\\\192.168.0.5\\c\\mirc\\logs\\#dagodz.QuakeNet.log");
				File file = new File("c:\\temp\\test.txt");
				System.out.println("File success");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
//				ArrayList<String> time = new ArrayList<String>();
								
				Nicks quoteNicks = new Nicks();
				
				// for QuoteLine
				
				
				
				// to handle change of date
				
				int month = 1;
				int year = 2015;
				int day = 1;
				
				//0 = time, 1 = person, 2 = line
				while ((line = bufferedReader.readLine()) != null) {
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
									
									//new
									quoteNicks.AddNick(line.substring(9,endNick));
																										
									Nicks.IncrementNick(line.substring(9,endNick));
									
									// new class testing
									
									QuoteLine quoteLine = new QuoteLine();
									quoteLine.Add(quoteTime, line.substring(9,endNick), line.substring(endNick + 2, line.length()));
									quoteLines.add(quoteLine);
									
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
		