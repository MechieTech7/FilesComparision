package filesComparison;

import java.io.*;
import java.util.Scanner;

public class CompareTextFiles {
    boolean res;
        String filePath(){
            String userDirectory = System.getProperty("user.dir");
            String sepr = System.getProperty("file.separator");
            String path = userDirectory+sepr+"src"+sepr+"FilesComparison"+sepr+"Files"+sepr;
            return path;
        }
        boolean compareTextFiles(String file1, String file2)  {
            String c1, c2;
            try {

                Reader fileOne =new FileReader(file1);
                Reader fileTwo =new FileReader(file2);
                BufferedReader r1 = new BufferedReader(fileOne);
                BufferedReader r2 = new BufferedReader(fileTwo);
                c1 = r1.readLine();
                c2 = r2.readLine();
                if (c1.contentEquals(c2))
                    res = true;
                else if (c2.contentEquals(c2)) {
                    res = false;
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("line 33");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return res;
        }

        void creatingFile(int words, String path) {
            try {
                String resPath = path+"result.json";
                FileWriter fileWriter = new FileWriter(resPath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                String wordsNum= Integer.toString(words);
                String result="Number of Words:";
                bufferedWriter.write(result);
                bufferedWriter.write(wordsNum);
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Notification = File is not created.");
            }
        }

        int countWords(String file1){

            int numWords = 0;
            Scanner myFile;
            try {
                myFile = new Scanner(new File(file1));
                while(myFile.hasNextLine()) {
                    String curLine = myFile.nextLine();
                    int size = curLine.length();
                    boolean foundDiv = true ;
                    boolean foundChar;
                    for (int LineSize = 0; LineSize < size ; LineSize++) {
                        if(curLine.charAt(LineSize) == ' ') {
                            foundDiv = true;
                            foundChar = false;
                        }else {
                            foundChar = true;
                        }
                        if(foundChar && foundDiv) {
                            numWords++;
                            foundDiv = false;
                        }
                    }
                }
                myFile.close();
            } catch (FileNotFoundException ioException) {
                System.out.println("Your file does not exist.");
            }

            return numWords;
        }
        public static void main(String[] args) {
            CompareTextFiles compare = new CompareTextFiles();
            String path = compare.filePath();
            String file1 = path+"1.txt";
            String file2 = path+"3.txt";
            //String userDirectory = System.getProperty("user.dir");
            //String sepr = System.getProperty("file.separator");
            //String file1 = userDirectory+sepr+"src"+sepr+"filesComparison"+sepr+"files"+sepr+"1.txt";
            //String file2 = userDirectory+sepr+"src"+sepr+"filesComparison"+sepr+"files"+sepr+"3.txt";
            //String res = userDirectory+sepr+"src"+sepr+"filesComparison"+sepr+"files"+sepr+"result.txt";
            boolean result = compare.compareTextFiles(file1,file2);
            if (result) {
                System.out.println("Files are same");
                System.out.println("Generating result file...");
                int words = compare.countWords(file1);
                compare.creatingFile(words,path);
                System.out.println("File Generated");
            } else {
                System.out.println("Files are different");
            }
        }
    }
