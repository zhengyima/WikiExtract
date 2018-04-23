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


public class RuleHandler {
	
	//String rulePath = "Data/RuleData/EntityCubeFactExtData/titlePattern.txt";
	String rulePath;
	String RelationTagdicPath;
	ArrayList<ArrayList<String>> rules;
	HashMap<String,ArrayList<String>> constPart;
	HashMap<String,String> dynamicPart;
	
	RuleHandler(String path,String rtpath){
		rulePath = path;
		RelationTagdicPath = rtpath;
		readRules();
		readConstPart();
		readRTfromTXT();
		readDynamicPart();
	}
	
	public void readRules(){
		rules = new ArrayList<ArrayList<String>>();
		
		File file1 = new File(rulePath); // 创建File类对象  
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
            		if(lineTxt.startsWith("[")) {
            			//System.out.println(lineTxt); // 逐行输出文件内容 
                		ArrayList<String> rule  = new ArrayList<String>();
	    	        		Pattern pattern = Pattern.compile("\\[([^\\[\\]]+)\\]");  
	    	        		Matcher matcher = pattern.matcher(lineTxt);
	    	        		while(matcher.find()){
	    	        			String str = matcher.group().replaceAll("\\[", "").replaceAll("\\]", "");
	    	        			rule.add(str);
	    	        		}
	    	        		rules.add(rule);
	            	}  
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
	}
	
	public void readConstPart() {
		constPart = new HashMap<String,ArrayList<String>>();
		
		File file1 = new File(rulePath); // 创建File类对象  
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
            		if(lineTxt.startsWith("#define")) {
            			String[] lineTxt_split = lineTxt.split(" ");
            			ArrayList<String> constWord = new ArrayList<String>();
            			
            			String[] words = lineTxt_split[2].replaceAll("\\[", "").replaceAll("\\]", "").split("/");
            			for(int i=0;i<words.length;i++) {
            				constWord.add(words[i]);
            			}
            			constPart.put(lineTxt_split[1], constWord);
            		}
                //System.out.println(lineTxt); // 逐行输出文件内容 
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
	}
	public void readRTfromTXT() {
		ArrayList<String> pages = new ArrayList<String>();
		ArrayList<String> RelationTags = new ArrayList<String>();
		
		File file1 = new File(RelationTagdicPath); // 创建File类对象  
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
                RelationTags.add(lineTxt);
            }  
            constPart.put("RelationTag", RelationTags);
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
	
	public void readDynamicPart() {
		dynamicPart = new HashMap<String,String>();
		dynamicPart.put("Person@t1", "PERSON");
		dynamicPart.put("Orgnization@t2", "ORGANIZATION");
	}
	
	public boolean wordIsTag(Sentence sent,int index,String tag,List<String> nerTags) {
		String word = sent.word(index).toLowerCase();
		//System.out.println(word+"is"+tag+"?");

		if(constPart.containsKey(tag)) {
			ArrayList<String> constWords = constPart.get(tag);
			for(int i=0;i<constWords.size();i++) {
				if(word.equals(constWords.get(i).toLowerCase())) {
					return true;
				}
			}
			return false;
		}
		else if(dynamicPart.containsKey(tag)) {
			String nertag = dynamicPart.get(tag);
			if(nerTags.get(index).equals(nertag)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(word.equals(tag)){
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public ArrayList<Integer> findStartPoint(int rule_index,Sentence sent,List<String> nerTags){
		ArrayList<String> rule = rules.get(rule_index);
		String head_tag = rule.get(0);
		
		ArrayList<Integer> start_points = new ArrayList<Integer>();
		for(int j=0;j<sent.length();j++) {
			if(j>0) {
				if(wordIsTag(sent,j,head_tag,nerTags) && (!wordIsTag(sent,j-1,head_tag,nerTags))) {
					start_points.add(j);
				}
			}
			else {
				if(wordIsTag(sent,j,head_tag,nerTags)) {
					start_points.add(j);
				}
			}
		}
		return start_points;
	}
	
	public ArrayList<HashMap<String,String>> match(Sentence sent){
		ArrayList<HashMap<String,String>> res = new ArrayList<HashMap<String,String>>();
		
		List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
		//System.out.println(nerTags);
		//String firstPOSTag = sent.posTag(0);   // NNP
		
		for(int i=0;i<rules.size();i++) {
			ArrayList<String> rule = rules.get(i);
			ArrayList<Integer> start_points = findStartPoint(i,sent,nerTags);
			//System.out.println(start_points);
			if(!start_points.isEmpty()) {
				String per = "";
				String org = "";
				for(int j=0;j<start_points.size();j++) {
					/* 算法部分 */
					int p = start_points.get(j);
					int q = 0;
					boolean flag=false;
					for(;q<rule.size();) {
						//System.out.println("p:"+p);
						//System.out.println("q:"+q);
						if(p>=sent.length()) {
							break;
						}
						if(wordIsTag(sent,p,rule.get(q),nerTags)) {
							
							if(rule.get(q).equals("Person@t1")) {
								per += sent.word(p)+" ";
							}
							if(rule.get(q).equals("Orgnization@t2")) {
								org += sent.word(p)+" ";
							}
							//System.out.println(sent.word(p)+"#"+rule.get(q)+"#"+q+"#"+(rule.size()-1));
							if(q==rule.size()-1) {
								flag = true;
							}
							p++;
							continue;
						}
						else {
							//System.out.println(sent.word(p)+"$"+rule.get(q)+"$"+q+"$"+(rule.size()-1));
							q++;
							if(q>=rule.size()) {
								break;
							}
							if(!wordIsTag(sent,p,rule.get(q),nerTags)) {
								//System.out.println("wow!");
								break;
							}
							else if(q==rule.size()-1) {
								//System.out.println(sent.word(p)+"%"+rule.get(q)+"%"+q+"%"+(rule.size()-1));
								flag = true;
							}
						}
					}
					/*if(q<rule.size()-1) {
						System.out.println("no match:(");
					}
					else {
						System.out.println("match:)");
					}*/
					//System.out.println("p:"+p);
					//System.out.println("q:"+q);
					if(!flag) {
						//System.out.println("no match:)");
					}
					else{
						System.out.println("match:)");
						System.out.println("person:"+per);
						System.out.println("orgnization:"+org);
					}
					per = "";
					org = "";
				}
			}
			else {
				continue;
			}
		}

		return res;
		
	}
	
	
	public static void  main(String args[]) {
		//System.out.println(readRules(rulePath));
		RuleHandler rh = new RuleHandler("Data/RuleData/EntityCubeFactExtData/titlePattern.txt2.bk","Data/RuleData/EntityCubeFactExtData/lstRelation.txt");
		//System.out.println(rh.rules);
		//System.out.println(new Sentence("Ma Zhengyi's dog is an advisors at WTO.").word(2));
		rh.match(new Sentence("Ma Zhengyi is an advisors's , at WTO."));
		/*rh.match(new Sentence("Ma Zhengyi is  an advisors at a house."));
		rh.match(new Sentence("Ma Zhengyi is an advisors at Ma Zhengyi an."));
		rh.match(new Sentence("an Ma Zhengyi is an advisors at Boss an."));
		rh.match(new Sentence("Ma Zhengyi is an advisors at RUC an."));*/
	}
	
	

}
