WeiXinGroupSend是使用Android UiAutomator实现微信的群发功能

使用步骤：

一、准备工作：

--安装并配置Utf7Ime.apk(apk目录)，配置链接https://github.com/sumio/uiautomator-unicode-input-helper

--启动微信

Utf7Ime.apk什么作用？

Utf7Ime.apk解决了UiAutomator无法输入中文的问题。

二、执行脚本：

--进入到WeiXinGroupSend目录

--运行python run.py(如果没有安装python也没关系，在cmd中依次执行run.py中的四条语句)


执行效果：

---->

|	点击通讯录

|	点击通讯录列表元素

|	点发消息

|	输入msg，点击发送(msg: XXX, 新年快乐)

|	点击返回

----<
