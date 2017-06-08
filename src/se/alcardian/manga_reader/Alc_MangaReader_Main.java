package se.alcardian.manga_reader;

import java.io.File;
import java.io.IOException;
import se.alcardian.io.Alc_IO;

public class Alc_MangaReader_Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String location = "E:\\MangaReader\\";
		
		for(String x:args){
			downloadChapters(x, location, 100);
		}
	}
	
	public static void downloadChapters(String url, String savePath, int delay){
		int count = 0;
		
		for(String x:Alc_MangaReader.getChapters(url)){
			System.out.println("Chapter: "+x);
			for(String y:Alc_MangaReader.getAllPages(x)){
				if(count > 4){
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count = 0;
				}
				
				System.out.println("     Page: "+y);
				
				
				String temp = Alc_MangaReader.getImageURL(y);
				System.out.println("     Image: "+ temp+"");
				
				String mainFolder = y.replace("http://www.mangareader.net/", "");
				mainFolder = mainFolder.substring(0, mainFolder.indexOf("/")+1);
				mainFolder = mainFolder.replace("/", "\\");
				
				boolean isFirstPage = false;
				String folderName = y.replace("http://www.mangareader.net/", "");
				if(folderName.lastIndexOf("/") != folderName.indexOf("/")){
					folderName = folderName.substring(0, folderName.lastIndexOf("/"))+"\\";
					folderName = folderName.replace("/", " Ch");
				}else{
					//folderName = folderName.substring(0, folderName.indexOf("/")+2)+"\\";
					folderName += "\\";
					folderName = folderName.replace("/", " Ch");
					isFirstPage = true;
				}
				
				//System.out.println("     Save Path: "+ savePath + mainFolder + folderName);
				
				String imageName = y.replace("http://www.mangareader.net/", "");
				imageName = imageName.replace("/", "-");
				if(isFirstPage){
					imageName += "-1";
				}
				imageName += temp.substring(temp.lastIndexOf("."), temp.length());
				System.out.println("     File Name: "+imageName);
				System.out.println("     Save Path: "+ savePath + mainFolder + folderName + imageName +"\n");
				
				
				
				count++;
				
				//TODO finalize the downloading...
				//**********************
				
				if(new File(savePath + mainFolder + folderName).isDirectory()){	//Checks if the folder already exists.
					//System.out.println("Folder Exists!");
				}else{
					System.out.println("Directory does not exists! "+savePath + mainFolder + folderName);
					System.out.println("Creating directory: "+savePath + mainFolder + folderName);
					new File(savePath + mainFolder + folderName).mkdirs();
				}
				
				if(new File(savePath + mainFolder + folderName + imageName).isFile()){	//Checks if there is a file with the name already.
					System.out.println("The file aldready exists! \""+url+"\" was skipped."+"\n");
				}else{
					System.out.println("Saving : " + savePath + mainFolder + folderName + imageName);
					System.out.println(temp+"\n");
					try {
						Alc_IO.download(temp, savePath + mainFolder + folderName + imageName);
					} catch (IOException e1) {
						System.out.println("Failed to write image: "+temp);
						e1.printStackTrace();
					}
				}
				
			}
		}
	}
}
