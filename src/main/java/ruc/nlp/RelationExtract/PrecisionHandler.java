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

public class PrecisionHandler {
	
	static String relStr = "leaders";
	static String relListPath = "Data/Per/"+relStr+".txt";
	static ArrayList<HashMap<String,String>> relList;
	
	/*static String txtPath = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/xml_out_all_text.txt";
	static String out_path = "C:/Users/jd/eclipse-workspace/RelationExtract/Data/PrecisionPersonRelation.out";*/
	static String txtPath = "Data/xml_out_all_text.txt";
	static String out_path = "Data/Per/"+relStr+".out";
	
	public static void readRelfromTXT() {
		ArrayList<String> pages = new ArrayList<String>();
		
		File file1 = new File(relListPath); // 创建File类对象  
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
            		String[] line_split = lineTxt.split("\t");
            		HashMap<String,String> hm = new HashMap<String,String>();
            		hm.put("full_name", line_split[0]);
            		hm.put("name1", line_split[1]);
            		hm.put("key", line_split[2]);
            		hm.put("name2", line_split[3]);
            		hm.put("flag",line_split[4]);
            		relList.add(hm);
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
	
	public static void ExtractHandler(String tname,ArrayList<String> sentences) {
		//System.out.println("in Extract!");
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
			if(i%50 == 0) {
				//System.out.println(i);
			}
			
			
			
			
			
			
			String sentence = sentences.get(i);
			
			for(int j=0;j<relList.size();j++) {
				if(sentence.toLowerCase().contains(relList.get(j).get("key").toLowerCase()) && 
					sentence.toLowerCase().contains(relList.get(j).get("name1").substring(0, relList.get(j).get("name1").length()-1).toLowerCase()) && 
					sentence.toLowerCase().contains(relList.get(j).get("name2").substring(0, relList.get(j).get("name2").length()-1).toLowerCase())){
					Sentence sent = new Sentence(sentence);
					List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
					String firstPOSTag = sent.posTag(0);   // NNP
					if(nerTags.contains("PERSON")) {
								String person1 = "";
								int person2_start =  0;
								for(int k=0;k<nerTags.size();k++) {
									//System.out.println(nerTags.get(k));
									if(nerTags.get(k).equals("PERSON")) {
										person1 += sent.word(k)+" ";
									}
									if(nerTags.size()>1 && k+1<nerTags.size() && nerTags.get(k).equals("PERSON") && (!nerTags.get(k+1).equals("PERSON"))) {
										person2_start = k+1;
										break;
									}
									
								}
								if(person1=="") {
									continue;
								}
								
								String person2 = "";
								for(int k=person2_start;k<nerTags.size();k++) {
									//System.out.println(nerTags.get(k));
									if(nerTags.get(k).equals("PERSON")) {
										person2 += sent.word(k)+" ";
									}
									if(nerTags.size()>1 && nerTags.get(k).equals("PERSON") && (!nerTags.get(k+1).equals("PERSON"))) {
										break;
									}
									
								}
								if(person2=="") {
									continue;
								}
								
								//System.out.println("person1 and person2 not null");
								
								String flag= "";
								if(tname.toLowerCase().contains(person1.toLowerCase().replaceAll(" ", ""))) {
									flag = "1";
								}
								else if(tname.toLowerCase().contains(person2.toLowerCase().replaceAll(" ", ""))) {
									flag = "2";
								}
								else {
									flag = "0";
									//System.out.println("name1:#"+person1+"#\nname2:#"+person2+"#\n");
									//System.out.println("tname:"+tname);
									//System.out.println("no name!");
									continue;
								}
								
								/*
								String org = "";
								for(int k=0;k<nerTags.size();k++) {
									if(nerTags.get(k).equals("ORGANIZATION")) {
										org += sent.word(k);
										//System.out.println(sent.word(k));
									}
								}
								System.out.println("Organization:"+org);
								*/
								
								//System.out.println("RelationTags:"+RelationTags.get(j));
								
								
								try {
						        		FileWriter writer=new FileWriter(out_path,true);
						        		
								    //writer.write(fs.get(i)+"\n");
						        		writer.write(tname+"\t"+person1+"\t"+relList.get(j).get("key")+"\t"+person2+"\t"+flag+"\t"+sentence+"\n");
								    writer.close();
						        }catch(Exception e2) {
						        		
						        }
							
						
					}
				}
			}
			

			//System.out.println(nerTags);
		}
	}
	
	
	public static void main(String args[]) {
		relList = null;
		relList = new ArrayList<HashMap<String,String>>();
		
		ArrayList<String> pages = ReadfromTXT(txtPath);
		//String[] keywords = {"ministers","supporters"};
		String[] keywords = {"friends","candidates"};
		
		for(int iter = 0;iter<keywords.length;iter++) {
			relStr = keywords[iter];
			relListPath = "Data/Per/"+relStr+".txt";
			txtPath = "Data/xml_out_all_text.txt";
			out_path = "Data/Per/"+relStr+".out";
			System.out.println("word:"+relStr);
			readRelfromTXT();
			for(int i = 0;i<pages.size();i++) {
				try {
					System.out.println("---"+i+" ---");
					HashMap<String,String> sh_map = SegmentHandler(pages.get(i));
					ExtractHandler(sh_map.get("name").toLowerCase(),FilterHandler(sh_map));
				}catch(Exception e) {
					continue;
				}
				
			}
			relList = null;
			relList = new ArrayList<HashMap<String,String>>();
			
		}
		
		//System.out.println(relList);
		
	}
	
	

}
