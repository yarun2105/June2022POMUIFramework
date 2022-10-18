package com.qa.opencart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Select select;
	private Actions act;
	private JavaScriptUtil jsUtil;

	// private static final String ELEMENT_NOT_FOUND_ERROR = "element is not
	// available on the page : ";

	public ElementUtil(WebDriver driver) { // constructor

		this.driver = driver;
		act = new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public void doSendkeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);

	}

	public WebElement getElement(By locator) {
		// return driver.findElement(locator);

		WebElement ele = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String getAttribute(By locator, String attrValue) {
		return getElement(locator).getAttribute(attrValue);
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public boolean doEleIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

// to know no. of webElement on the Webpages

	public ArrayList<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String Text = e.getText();
			if (Text.length() != 0) {
				eleTextList.add(Text);
			}

		}
		return eleTextList;
	}

	// to know WebElement from footer

	public ArrayList<String> getFooterlinkList(By locator) {
		List<WebElement> footerList = driver.findElements(locator);
		System.out.println(footerList.size());

		ArrayList<String> eleList = new ArrayList<String>();

		for (WebElement e : footerList) {
			String text = e.getText();
			eleList.add(text);

			System.out.println(eleList);
		}

		return eleList;
	}

	// ************ DropDown Select method generic*********************
	// only when html tag is <Select>

	public void doSelectDropDownByIndex(By locator, int index) {

		select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String text) {

		select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropDownByValue(By locator, String value) {

		select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public List<WebElement> getDropDownOptions(By locator) {
		select = new Select(getElement(locator));
		return select.getOptions();
	}

	public int getDropDownOptionsCount(By locator) {
		return getDropDownOptions(locator).size();
	}

	// when select tag is not available

	public void doSelectfromDropDown(By locator, String values) {

		List<WebElement> optionList = getElements(locator);
		System.out.println(optionList.size());

		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(values)) {
				e.click();
				break;
			}
		}
	}

	// search method from any search text box when select tag is not present example
	// google search

	public void doSearch(By searchLocator, String searchkey, By suggesLocator, String value)
			throws InterruptedException {

		getElement(searchLocator).sendKeys(searchkey);

		Thread.sleep(3000);

		List<WebElement> suggestionList = getElements(suggesLocator);
		System.out.println(suggestionList.size() - 1);

		for (WebElement e : suggestionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;

			}

		}

	}

	// this method is for search element search text box

	public void doSearch(String tagName, String text) {
		By suggLocator = (By.xpath("//" + tagName + "[text()='" + text + " ']"));
		driver.findElement(suggLocator).click();
	}

	// this method is used when element is present or not same like isDisplayed
	// method

	public boolean isSingleElementPresent(By locator) {
		List<WebElement> List = driver.findElements(locator);
		System.out.println(List.size());

		if (List.size() == 1) {
			System.out.println("single search ele is present on the page");
			return true;
		} else {
			System.out.println("no search or multiple search element is present");
			return false;
		}
	}

	// ********************************************Actions
	// Class*************************

	// moveElement or mousehover method

	public void moveToElementLevel1(By parentLocator, By Childlocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentLocator)).build().perform();
		Thread.sleep(3000);
		// getElement(Childlocator).click();
		doClick(Childlocator);
	}

	public void moveToElementLevel2(By parent1Locator, By parent2Locator, By Childlocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent1Locator)).build().perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(parent2Locator)).build().perform();
		Thread.sleep(3000);
		// getElement(Childlocator).click();
		doClick(Childlocator);
	}

	// Right click action method

	public void doRightClick(By rightclicklocator, By optionslocator) {
		WebElement rightClick = getElement(rightclicklocator);
		Actions act = new Actions(driver);
		act.contextClick(rightClick).build().perform();
		List<WebElement> optionsList = getElements(optionslocator);
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text1 = e.getText();
			System.out.println(text1);
			if (text1.equals("Copy")) {
				e.click();

				break;
			}
		}

	}

	// Drag and Drop Element method

	public void dragAndDrop(By draggableLocator, By droppableLocator) {
		WebElement sourceEle = getElement(draggableLocator);
		WebElement targetEle = getElement(droppableLocator);

		// Actions act = new Actions(driver);

		// act.clickAndHold(sourceEle).moveToElement(targetEle).release().build().perform();
		// //builder pattern

		act.dragAndDrop(sourceEle, targetEle).build().perform();
	}

	// Actions Sendkeys and click method

	public void doActionSendKeys(By locator, String value) {
		// Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {
		// Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	// ***********************WaitUtils*********************

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * // This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void doSendKeysWithWait(By locator, int timeOut, String value) {
		waitForElementPresence(locator, timeOut).sendKeys(value);
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForElementPresence(locator, timeOut).click();
	}

	public String getTextWithWait(By locator, int timeOut) {
		return waitForElementPresence(locator, timeOut).getText();
	}

	public boolean elementisDisplayedWithWait(By locator, int timeOut) {
		return waitForElementPresence(locator, timeOut).isDisplayed();
	}

	public String getAttributeWithWait(By locator, int timeOut, String attrValue) {
		return waitForElementPresence(locator, timeOut).getAttribute(attrValue);
	}

	/**
	 * // An expectation for checking that an element is present on the DOM of a
	 * page // and visible.Visibility means that the element // is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void doSendKeysWithVisibleElement(By locator, int timeOut, String value) {
		waitForElementVisible(locator, timeOut).sendKeys(value);
	}

	public void doClickWithVisibleElement(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	public String getTextWithVisibleElement(By locator, int timeOut) {
		return waitForElementVisible(locator, timeOut).getText();
	}

	public boolean elementisDisplayedVisibleElement(By locator, int timeOut) {
		return waitForElementVisible(locator, timeOut).isDisplayed();
	}

	public String getAttributeWithVisibleElement(By locator, int timeOut, String attrValue) {
		return waitForElementVisible(locator, timeOut).getAttribute(attrValue);
	}

	// *********java script alert handle with wait.***********

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void acceptAlertPopUp(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismisAlertPopUp(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void alertSendkeys(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

	// **********wait for Title ****************

	public String waitForTitleContains(int timeOut, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitFortitleIs(int timeOut, String tilteValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(tilteValue))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	// *****************wait for url******************

	public String waitForUrlContains(int timeOut, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		} else {
			return null;

		}
	}

	public String waitForUrlIs(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		} else {
			return null;

		}
	}

	// ********************wait for Frame******************** Method overloading
	// **********

	public void waitForFrame(int timeOut, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrame(int timeOut, String nameOrId) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

	public void waitForFrame(int timeOut, WebElement FrameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
	}

	public void waitForFrame(int timeOut, By FrameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameLocator));
	}

}
