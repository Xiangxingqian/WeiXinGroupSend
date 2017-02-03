import java.util.ArrayList;
import java.util.List;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

/**
 * 
 * @author xiangxingqian
 */
public class LaunchSettings extends UiAutomatorTestCase {

	public static List<String> excludedList = new ArrayList<String>();
	static {
		excludedList.add("编辑标签");
		excludedList.add("保存");
		excludedList.add("标签名字");
		excludedList.add("成员");
	}
	/**
	 * 记录点击过的人的姓名
	 */
	private List<String> clickedPerson = new ArrayList<String>();
	public static final String haha = "手机测试用";

	private boolean isEnd;

	public void test() {
		clickView("通讯录");
		while (true) {
			clickContactList();
			if (isEnd) {
				scrollView();
				break;
			}
		}
	}

	private void clickBack() {
		getUiDevice().pressBack();
	}

	/**
	 * 向前滚动
	 */
	private void scrollView() {
		UiScrollable ui = new UiScrollable(new UiSelector().className(ListView.class));
		try {
			ui.scrollForward(60);
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		sleep(1000l);
	}

	public void clickContactList() {
		sleep(200);
		Log.e("qian", "jalhg");
		UiCollection collection = new UiCollection(new UiSelector().className(ListView.class));
		int childCount = collection.getChildCount(new UiSelector().className(View.class));
		try {
			for (int i = 0; i < childCount; i++) {
				UiObject viewChild = collection.getChildByInstance(new UiSelector().className(View.class), i);
				String text = viewChild.getText();
				if (text.contains("{")) {
					text = text.substring(text.indexOf("{") + 1, text.indexOf("}"));
				}
				if (isValidText(text)) {
					viewChild.click();// 点击list item
					clickView("发消息");// 点击发消息
					sendMsg(text + ", " + haha);// 发送消息
					clickBack();// 点击返回键
					clickView("通讯录");// 点通讯录
				}
			}
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void clickPosition(int x, int y) {
		UiDevice uiDevice = getUiDevice();
		sleep(200);
		uiDevice.click(x, y);
	}

	/**
	 * 是否是人名
	 * 
	 * @param text
	 * @return
	 */
	private boolean isValidText(String text) {
		if (excludedList.contains(text)) {
			return false;
		}
		if (text.equals("周志俊")) {
			isEnd = true;
		}
		if (text.matches("[A-Z]")) {
			return false;
		}
		if (text.matches("[a-zA-Z\\s]+")) {
			Log.e("qian", "Foreign friend: " + text);
			return false;
		}
		if (clickedPerson.contains(text)) {
			return false;
		} else {
			clickedPerson.add(text);
		}
		return true;
	}

	/**
	 * 按文字点击
	 * 
	 * @param text
	 */
	public void clickView(String text) {
		try {
			UiObject uiObject = new UiObject(new UiSelector().text(text));
			uiObject.click();
		} catch (UiObjectNotFoundException e) {
			sleep(1 * 1000);
		}
		sleep(1000);
	}

	/**
	 * 发送消息
	 * 
	 * @throws UiObjectNotFoundException
	 */
	private void sendMsg(String msg) {
		try {
			UiObject uiObject = new UiObject(new UiSelector().className(EditText.class));
			uiObject.setText(Utf7ImeHelper.e(msg));
			sleep(5000l);
			clickView("发送");
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
	}
}
