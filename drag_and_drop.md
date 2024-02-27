# Drag and Drop

Unfortunately, the `WebDriver` API and its implementations carry inherent bugs. One of these is that the Selenium API for dragging and dropping elements does not always 
function as intended.

To resolve this, use the static method in the following class (you will need to add this to your project):

```java
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helpers {

	/**
	 * Workaround for broken WebDriver drag and drop API.<br>
	 * <br>
	 * Source: <a href="https://stackoverflow.com/questions/27742390/drag-and-drop-with-selenium-webdriver-on-java">StackOverflow</a>
	 * @param source
	 * @param destination
	 * @param driver
	 * @throws Exception
	 */
	public static void dragAndDrop(WebElement source, WebElement destination, WebDriver driver) throws Exception {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("function createEvent(typeOfEvent) {\n" 
		    		   + "    var event = document.createEvent(\"CustomEvent\");\n" 
		    		   + "    event.initCustomEvent(typeOfEvent,true, true, null);\n" 
		    		   + "    event.dataTransfer = {\n" +"data: {},\n" 
		    		   + "        setData: function (key, value) {\n" 
		    		   + "            this.data[key] = value;\n" 
		    		   + "        },\n" 
		    		   + "        getData: function (key) {\n" 
		    		   + "            return this.data[key];\n" 
		    		   + "        }\n" 
		    		   + "    };\n" 
		    		   + "    return event;\n" 
		    		   + "}\n" 
		    		   + "\n" 
		    		   + "function dispatchEvent(element, event,transferData) {\n" 
		    		   + "    if (transferData !== undefined) {\n" 
		    		   + "        event.dataTransfer = transferData;\n" 
		    		   + "    }\n" 
		    		   + "    if (element.dispatchEvent) {\n" 
		    		   + "        element.dispatchEvent(event);\n" 
		    		   + "    } else if (element.fireEvent) {\n" 
		    		   + "        element.fireEvent(\"on\" + event.type, event);\n" 
		    		   + "    }\n" 
		    		   + "}\n" 
		    		   + "\n" 
		    		   + "function simulateHTML5DragAndDrop(element, destination) {\n" 
		    		   + "    var dragStartEvent =createEvent('dragstart');\n" 
		    		   + "    dispatchEvent(element, dragStartEvent);\n" 
		    		   + "    var dropEvent = createEvent('drop');\n" 
		    		   + "    dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" 
		    		   + "    var dragEndEvent = createEvent('dragend');\n" 
		    		   + "    dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" 
		    		   + "}\n" 
		    		   + "\n" 
		    		   + "var source = arguments[0];\n" 
		    		   + "var destination = arguments[1];\n" 
		    		   + "simulateHTML5DragAndDrop(source,destination);", source, destination);
	}
}
```

## Usage

Using the method is simple, call it and pass a driver alongside the element being dragged and the drop target:

```java
WebElement draggable = driver.findElement(By.id("draggable"));
WebElement dropTarget = driver.findElement(By.id("drop-target"));

Helpers.dragAndDrop(draggable, dropTarget, driver);
```