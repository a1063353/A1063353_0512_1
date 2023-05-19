import java.util.function.Function;
import java.util.Random;
import java.util.Scanner;
import java.util.Random;

		public class A1063353_0512_1 {
		    private static int PORK_DUMPLINGS_STOCK = 5000;
		    private static int BEEF_DUMPLINGS_STOCK = 3000;
		    private static int VEGETABLE_DUMPLINGS_STOCK = 1000;

		    public static void main(String[] args) {
		        // 使用者輸入同時光顧的顧客數目
		    	

		        System.out.println("請輸入顧客數:");
		        Scanner s=new Scanner(System.in);
		        String name = s.next();
		        int numberOfCustomers = 0;
		        try 
		        {
		        	 numberOfCustomers =Integer.parseInt(name);
		        	 System.out.println("您輸入的顧客數量為:"+numberOfCustomers);
		        	
		        }catch(NumberFormatException e) {
		        	
		       	 System.out.println("請輸入正確的顧客數量:");
		        }
		       // int numberOfCustomers = 1000; // 假設有5位顧客

		        // 建立服務生
		        Waiter waiter = new Waiter();

		        // 建立顧客執行緒
		        for (int i = 1; i <= numberOfCustomers; i++) {
		            Thread customerThread = new Thread(new Customer(waiter, "顧客 " + i));
		            customerThread.start();
		        }
		    }

		    static class Waiter {
		        private final Object lock = new Object();

		        public void serve() {
		            try {
		                Thread.sleep(3000); // 等待服務生的時間
		               
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }

		        public void takeOrder(Customer customer, int quantity, String type) {
		            synchronized (lock) {
		                switch (type) {
		                    case "豬肉":
		                        if (quantity <= PORK_DUMPLINGS_STOCK) {
		                            PORK_DUMPLINGS_STOCK -= quantity;
		                            System.out.println(customer.getName() + ": 已訂購 " + quantity + "豬肉水餃");
		                        } else {
		                            System.out.println(customer.getName() + ":豬肉水餃賣完了");
		                        }
		                        break;
		                    case "牛肉":
		                        if (quantity <= BEEF_DUMPLINGS_STOCK) {
		                            BEEF_DUMPLINGS_STOCK -= quantity;
		                            System.out.println(customer.getName() + ":已訂購 " + quantity + "牛肉水餃");
		                        } else {
		                            System.out.println(customer.getName() + ":牛肉水餃賣完了");
		                        }
		                        break;
		                    case "蔬菜":
		                        if (quantity <= VEGETABLE_DUMPLINGS_STOCK) {
		                            VEGETABLE_DUMPLINGS_STOCK -= quantity;
		                            System.out.println(customer.getName() + ":已訂購 " + quantity + "蔬菜水餃");
		                        } else {
		                            System.out.println(customer.getName() + ":蔬菜水餃賣完了");
		                        }
		                        break;
		                }
		            }
		        }
		    }

		    static class Customer implements Runnable {
		        private Waiter waiter;
		        private String name;

		        public Customer(Waiter waiter, String name) {
		            this.waiter = waiter;
		            this.name = name;
		        }

		        public String getName() {
		            return name;
		        }

		        @Override
		        public void run() {
		            Random random = new Random();
		            int quantity = random.nextInt(41) + 10; // 產生10到50的亂數數量

		            // 隨機選擇水餃種類
					String[] dumplingTypes = {"豬肉", "牛肉", "蔬菜"};
					String type = dumplingTypes[random.nextInt(3)];

					// 點餐
					waiter.serve();
					waiter.takeOrder(this, quantity, type);
		        }
		    }
		}
