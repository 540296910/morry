package study.common;

public class Test{
    private static boolean mainThread=false;
    public static void main(String[] args){
        new Thread(new Runnable(){
            public void run() {
                for(int i=0 ; i<50; i++){
                    synchronized (Test.class) {
                        if(mainThread){
                            try {
                                Test.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        for(int j=0; j<10; j++){
                            System.out.print(Thread.currentThread().getName()+", i="+i+", j="+j);
                        }
                        mainThread=true;
                        Test.class.notify();
                    }
                }
            }
             
        }).start();
        for(int i =0; i<50; i++){
            synchronized (Test.class) {
                if(!mainThread){
                    try {
                        Test.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j=0; j<3000; j++){
                    System.out.print(Thread.currentThread().getName()+", i="+i+", j="+j);
                }
                mainThread=false;
                Test.class.notify();
            }
        }
    }
}