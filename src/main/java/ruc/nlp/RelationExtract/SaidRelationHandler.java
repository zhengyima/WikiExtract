package ruc.nlp.RelationExtract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.simple.*;

public class SaidRelationHandler {
	
	static String txtPath = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/xml_out_all_text.txt";
	//static String RelationTagdicPath = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/RuleData/EntityCubeFactExtData/lstRelation.txt";
	//static ArrayList<String> RelationTags = new ArrayList<String>();
	static String out_path = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/SaidRelation_12.out";
	static ArrayList<String> verbs = new ArrayList<String>(); 
	static String VerbdicPath = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/RuleData/EntityCubeFactExtData/verb.txt";
	//static String VerbdicPath = "Data/RuleData/EntityCubeFactExtData/verb.txt";
	static String multi_person_out_path = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/multi_12.out";
	
	public static void readVerbfromTXT() {
		ArrayList<String> pages = new ArrayList<String>();
		
		File file1 = new File(VerbdicPath); // 创建File类对象  
        FileInputStream fis = null; // 创建FileInputStream类对象读取File  
        InputStreamReader isr = null; // 创建InputStreamReader对象接收文件流  
        BufferedReader br = null; // 创建reader缓冲区将文件流装进去  
        try {  
            fis = new FileInputStream(file1);  
            isr = new InputStreamReader(fis);  
            br = new BufferedReader(isr);  
            String lineTxt = null;  
            // 从缓冲区中逐行读取代码，调用readLine()方法  
            while ((lineTxt = br.readLine()) != null) {  
                //System.out.println(lineTxt); // 逐行输出文件内容  
            		verbs.add(lineTxt);
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {  
            // 文件执行完毕别忘了关闭数据流  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            if (isr != null) {  
                try {  
                    isr.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fis != null) {  
                try {  
                    fis.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        //return pages;
	}
	
	public static ArrayList<String> ReadfromTXT(String path) {
		ArrayList<String> pages = new ArrayList<String>();
		
		File file1 = new File(path); // 创建File类对象  
        FileInputStream fis = null; // 创建FileInputStream类对象读取File  
        InputStreamReader isr = null; // 创建InputStreamReader对象接收文件流  
        BufferedReader br = null; // 创建reader缓冲区将文件流装进去  
        try {  
            fis = new FileInputStream(file1);  
            isr = new InputStreamReader(fis);  
            br = new BufferedReader(isr);  
            String lineTxt = null;  
            // 从缓冲区中逐行读取代码，调用readLine()方法  
            while ((lineTxt = br.readLine()) != null) {  
                //System.out.println(lineTxt); // 逐行输出文件内容  
                pages.add(lineTxt);
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  finally {  
            // 文件执行完毕别忘了关闭数据流  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            if (isr != null) {  
                try {  
                    isr.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fis != null) {  
                try {  
                    fis.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return pages;
    }  
	
public static String deleteLink(String content) {
		
		Pattern pattern = Pattern.compile("\\[\\[([^\\[\\]]+)\\]\\]");  
		Matcher matcher = pattern.matcher(content);
		
		
		while(matcher.find()){
			
			String linkunit_0 = matcher.group();
			String linkunit = matcher.group().replaceAll("\\[\\[", "").replaceAll("\\]\\]", "");
			//String linkunit = matcher.group();
			String linktext = linkunit;
			if(linkunit.contains("|")) {
				String[] linkunit_split = linkunit.split("\\|");
				//System.out.println(linkunit_split.length);
				
				linktext = linkunit_split[1];
			}
			
			//System.out.println(linkunit_0);
			//System.out.println(linkunit);
			//System.out.println(linktext);
			//System.out.println(content);
			//System.out.println(linkunit);
			//System.out.println(linktext);
			
			content = content.replace(linkunit_0, linktext);
		}
		
		pattern = Pattern.compile("\\[([^\\[\\]]+)\\]");  
		matcher = pattern.matcher(content);
		while(matcher.find()){
			
			String linkunit_0 = matcher.group();
			String linkunit = matcher.group().replaceAll("\\[", "").replaceAll("\\]", "");
			//String linkunit = matcher.group();
			String linktext = linkunit;
			if(linkunit.contains("|")) {
				String[] linkunit_split = linkunit.split("\\|");
				//System.out.println(linkunit_split.length);
				
				linktext = linkunit_split[1];
			}
			
			//System.out.println(linkunit_0);
			//System.out.println(linkunit);
			//System.out.println(linktext);
			//System.out.println(content);
			//System.out.println(linkunit);
			//System.out.println(linktext);
			
			content = content.replace(linkunit_0, "");
		}
		content.replaceAll("\\[\\]", "");
		
		//System.out.println(content);
		return content;
		
	}
	
	public static String deleteFlag(String content) {
		
		Pattern pattern = Pattern.compile("\\{\\{[^}]*\\}\\}");
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
			String linkunit_0 = matcher.group();
			//System.out.println(linkunit_0);
			//System.out.println("------");

			//System.out.println(linkunit_0);
			content = content.replace(linkunit_0, "");
		}
		
		return content;
		
	}
	
	public static HashMap<String,String> SegmentHandler(String page) {
		HashMap<String,String> hm = new HashMap<String,String>();
		
		String[] page_split_array = page.split("\t");
		String titleName = page_split_array[0];
		String content = page_split_array[1].replaceAll("'''", "");
		
		hm.put("name", titleName);
		
		
		//System.out.println(content);
		
		content = deleteFlag(deleteLink(content));
		//content = deleteFlag(deleteLink(content));
		hm.put("text",content);
		
		//System.out.println(hm.get("text"));
		return hm;
	}
	
	public static ArrayList<String> FilterHandler(HashMap<String,String> sh_map) {
		ArrayList<String> a_sentences = new ArrayList<String>();
		
		String[] sentences = sh_map.get("text").split("[.|;|?|!]");
		for(int i=0 ; i<sentences.length;i++) {
			if(sentences[i].length()<10 || sentences[i].startsWith("&")) {
				continue;
			}
			
			
			a_sentences.add(sentences[i]);
			//System.out.println(sentences[i]);
		}
		return a_sentences;
	}
	
	public static String TagshaveOnePerson(String tname,String sentence,Sentence sent,List<String> nerTags) {
		
		String person_name = "";
		
		if(!nerTags.contains("PERSON")) {
			return "";
		}
		if(nerTags.size() == 1) {
			return "";
		}
		
		/*
		for(int iter=0;iter<sent.length();iter++) {//iter -- said
			if(verbs.contains(sent.word(iter).toLowerCase())) {
				int iter2 = iter-1;
				for(iter2 = iter-1;iter2>0;iter2--) {
					if(nerTags.get(iter2).equals("PERSON")) {
						continue;
					}
					else {
						break;
					}
				}
				if(iter2<iter-1) {
					for(;iter2<iter;iter2++) {
						person_name += sent.word(iter2)+" ";
					}
					return person_name;
				}
			}
		}*/
		
		int i;
		for(i=1;i<nerTags.size();i++) {
			if(nerTags.get(i-1).equals("PERSON")) {
				person_name += sent.word(i-1)+" ";
			}
			if(nerTags.get(i-1).equals("PERSON") && (!nerTags.get(i).equals("PERSON"))) {
				break;
			}
		}
		for(int j=i+1;j<nerTags.size();j++) {
			if(nerTags.get(j).equals("PERSON")) {//如果含有多个人
				String my_person_name = "";
				for(int iter=0;iter<sent.length();iter++) {//iter -- said
					if(verbs.contains(sent.word(iter).toLowerCase())) {
						int iter2 = iter-1;
						for(iter2 = iter-1;iter2>0;iter2--) {
							/*if(nerTags.get(iter2).equals("PERSON")) {
								continue;
							}
							else {
								break;
							}*/
							if(nerTags.get(iter2).equals("0")) {
								break;
							}
							if(!nerTags.get(iter2).equals("PERSON")) {
								break;
							}
							
							//System.out.println("$"+nerTags.get(iter2)+sent.word(iter2));
						}
						
						iter2+=1;
						if(iter2<iter) {
							//System.out.println(nerTags);
							//System.out.println(sent.toString());
							//System.out.println(sent.word(iter2));
							for(;iter2<iter;iter2++) {
								my_person_name += sent.word(iter2)+" ";
							}
							//System.out.println("@@@"+my_person_name+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

							if(tname.toLowerCase().replaceAll(" ","").contains(my_person_name.toLowerCase().replaceAll(" ",""))){
								//System.out.println("+++"+my_person_name+"++++++++++++++++++++++++++++++++++++++++++");
								
								return my_person_name;
							}
							else {
								//System.out.println("&"+tname.toLowerCase()+"&");
								//System.out.println("&"+my_person_name.toLowerCase()+"&");
								//System.out.println("-+-+-+_+_+_+");
							}
						}
					}
				}
				return "#";
			}
		}
		return person_name;
	}
	
	
	public static void ExtractHandler(String tname,ArrayList<String> sentences) {
		System.out.println("in Extract!");
		//System.out.println(sentences);
		/*
		String sentence = sentences.get(0);
		System.out.println("1");
		Sentence sent = new Sentence("Zhengyi Ma was a powerful military commander in Renmin University of China during the resistance against the Soviet occupation between 1979 and 1989.");
		//Sentence sent = new Sentence("Lucy is in the sky with diamonds");
		
		System.out.println("2");
		List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]

		System.out.println("3");
		String firstPOSTag = sent.posTag(0);   // NNP
		System.out.println("4");
		System.out.println(nerTags);
		*/
		
		for(int i=0;i<sentences.size();i++) {
			//if(i%50 == 0) {
				//System.out.println(i);
			//}
			String sentence = sentences.get(i);
			Pattern pattern = Pattern.compile("\"([^\"\"]+)\"");
			Matcher matcher = pattern.matcher(sentence);
			
			if(matcher.find()) {//有引号
				//System.out.println("--level 1 complete!");
				String quote = matcher.group();
				
				boolean verb_flag = false;
				for(int j=0;j<verbs.size();j++) {/* judge contain verb */
					if(sentence.toLowerCase().contains(verbs.get(j))) {
						verb_flag = true;
						break;
					}
				}
				if(verb_flag) {
					//System.out.println("---------level 2 complete!");
					Sentence sent = new Sentence(sentence.replaceAll(quote, "\"good morning!\""));
					List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
					//String firstPOSTag = sent.posTag(0);   // NNP
					
					
					String Rightflag = TagshaveOnePerson(tname,sentence,sent,nerTags);
					if(Rightflag.equals("") || Rightflag.equals("#") || (!tname.toLowerCase().replaceAll(" ", "").contains(Rightflag.toLowerCase().replaceAll(" ", "")))) {//cannot find man
					//if(Rightflag.equals("") || Rightflag.equals("#") ) {//cannot find man
						if(Rightflag.equals("#")) {
							try {
				        		FileWriter writer=new FileWriter(multi_person_out_path,true);
						    //writer.write(fs.get(i)+"\n");
				        		writer.write(tname+"\t"+sentence+"\n");
				        		writer.close();
				        		continue;
					        }catch(Exception e2) {
					        	continue;
					        }
						}
					}
					else {
						//System.out.println("-----------------level 3 complete!");
						System.out.println("person:"+Rightflag+"\nquote:"+quote);
						
						String zh_flag = "0";
						if(quote.toLowerCase().contains("chinese") || quote.toLowerCase().contains("china")) {
							zh_flag = "1";
						}
						
						try {
				        		FileWriter writer=new FileWriter(out_path,true);
						    //writer.write(fs.get(i)+"\n");
				        		writer.write(tname+"\t"+Rightflag+"\t"+quote+"\t"+zh_flag+"\n");
						    writer.close();
				        }catch(Exception e2) {
				        		
				        }
					}
				}
				else {
					continue;
				}
			}
			else {
				continue;
			}
			
			
			/*
			List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
			String firstPOSTag = sent.posTag(0);   // NNP
			if(nerTags.contains("PERSON") && nerTags.contains("ORGANIZATION")) {
				for(int j=0;j<RelationTags.size();j++) {
					if(sentence.contains(RelationTags.get(j))) {
						String person = "";
						for(int k=0;k<nerTags.size();k++) {
							//System.out.println(nerTags.get(k));
							if(nerTags.get(k).equals("PERSON") && tname.contains(sent.word(k).toLowerCase())) {
								person += sent.word(k)+" ";
							}
						}
						if(person=="") {
							continue;
						}
						System.out.println("Person:"+person);
						
						String org = "";
						for(int k=0;k<nerTags.size();k++) {
							if(nerTags.get(k).equals("ORGANIZATION")) {
								org += sent.word(k);
								//System.out.println(sent.word(k));
							}
						}
						System.out.println("Organization:"+org);
						System.out.println("RelationTags:"+RelationTags.get(j));
						try {
				        		FileWriter writer=new FileWriter(out_path,true);
						    //writer.write(fs.get(i)+"\n");
				        		writer.write(tname+"\t"+person+"\t"+RelationTags.get(j)+"\t"+org+"\n");
						    writer.close();
				        }catch(Exception e2) {
				        		
				        }
					}
				}
			}*/
			//System.out.println(nerTags);
		}
	}
	public static void main(String args[]) {
		/*
		readVerbfromTXT();
		System.out.println(verbs.get(0));
		
		Sentence sent = new Sentence("Zhengyi Ma said that,\"test \"");
		List<String> nerTags = sent.nerTags();  
		
		System.out.println(TagshaveOnePerson(sent,nerTags));
		
		*/
		
		readVerbfromTXT();
		ArrayList<String> pages = ReadfromTXT(txtPath);
		for(int i = 0;i<pages.size();i++) {
			
			try {
				System.out.println("---"+i+" ---");
				HashMap<String,String> sh_map = SegmentHandler(pages.get(i));
				ExtractHandler(sh_map.get("name").toLowerCase(),FilterHandler(sh_map));
			}catch(Exception e) {
				continue;
			}
			
		}

		

	}

}
