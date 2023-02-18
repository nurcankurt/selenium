import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.*;

public class GetPrice {
    WebDriver driver;
    String item = "iphone 13 128 ";
    HashMap<String,Double>priceList = new HashMap<String, Double>();

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterMethod
    public void close(){
        driver.close();
    }


    @Test
    public void test02(){
        driver.get("https:www.teknosa.com");
        WebElement searchbox2 = driver.findElement(By.id("search-input"));
        searchbox2.sendKeys(item);
        searchbox2.sendKeys(Keys.ENTER);
        WebElement value = driver.findElement(By.xpath("(//div[@class='prd-prc2'])[1]"));
        double price = getPriceValue(value.getText());
        priceList.put("teknosa",price);
    }

    @Test
    public void test04(){
        driver.get("https:www.vatanbilgisayar.com");
        WebElement searchBox3 = driver.findElement(By.id("navbar-search-input"));
        searchBox3.sendKeys(item);
        searchBox3.sendKeys(Keys.ENTER);
        WebElement value = driver.findElement(By.className("product-list__cost"));
        //System.out.println(price3.getText());
        double price = getPriceValue(value.getText());
        //System.out.println(price);
        priceList.put("vatanbilgisyar",price);

    }
    @Test
    public void test05(){
        driver.get("https:www.trendyol.com");
        WebElement searchBox = driver.findElement(By.className("V8wbcUhU"));
        searchBox.sendKeys(item);
        searchBox.sendKeys(Keys.ENTER);
        WebElement value = driver.findElement(By.className("prc-box-dscntd"));
        double price = getPriceValue(value.getText());
        //System.out.println(price);
        priceList.put("trendyol",price);

    }

    public double getPriceValue(String str){
        String[] splited = str.split("\\s+");
        String temp = splited[0].replace(".","");
        temp=temp.replace(",",".");
        return Double.parseDouble(temp);

    }
    @AfterClass
    public void list(){
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        ArrayList<Double> list = new ArrayList<>();

        for (Map.Entry<String, Double> entry : priceList.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (Double num : list) {
            for (Map.Entry<String, Double> entry : priceList.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        System.out.println(sortedMap);
    }
    }



