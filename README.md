##Android 事件分发机制


### ViewGroup 


#### dispatchTouchEvent 返回true

	dispatchTouchEvent: Activity    ACTION_DOWN
	MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
	dispatchTouchEvent: Activity    ACTION_UP
	MyrelativeLayout  dispatchTouchEvent:   ACTION_UP

> ViewGroup自己在dispatchTouchEvent中消费这个事件

####dispatchTouchEvent 返回false

		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP


> 回传到Activity中onTouchEvent消费事件

#### dispatchTouchEvent 返回 super.dispatchTouchEvent(ev)子View也都返回super

		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOW
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DO
		 MyImageView dispatchTouchEvent:   ACTION_DOWN
		  MyImageView     onTouchEvent:   ACTION_DOWN
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP

ViewGroup返回dispatchTouchEvent,会调用ViewGroup中的onInterceptTouchEvent方法，
如果onInterceptTouchEvent返回super.onInterceptTouchEvent(ev)
就回向下传到子View
子View中调用dispatchTouchEvent与onTouchEvent方法
如果都返回的是super().再向上传到ViewGroup，
这时调用ViewGroup的onTouchEvent，如果返回为super，
刚再向上返回给Activity,调用Activity的onTouchEvent消费


#### ViewGroup中的onInterceptTouchEvent返回true,onTouchEvent返回super


		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP


事件从Activty的dispatchTouchEvent开始
调用ViewGroup的dispatchTouchEvent，此时这里返回super
会调用ViewGroup中的onInterceptTouchEvent拦截事件
onInterceptTouchEvent这里返回true，说明拦截
就会交给ViewGroup的onTouchEvent
onTouchEvent这里返回Suepr，不消费，就再向上传给Activity
最后由Activty中的onTouchEvent来消费


#### ViewGroup中的onInterceptTouchEvent返回true,onTouchEvent返回true(和上面相同最后由ViewGroup来消费)


		
		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DO
		 MyrelativeLayout onInterceptTouchEvent  ACTION_D
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		MyrelativeLayout  dispatchTouchEvent:   ACTION_UP
		MyrelativeLayout: onTouchEvent  ACTION_UP



事件从Activty的dispatchTouchEvent开始
调用ViewGroup的dispatchTouchEvent，此时这里返回super
会调用ViewGroup中的onInterceptTouchEvent拦截事件
onInterceptTouchEvent这里返回true，说明拦截
就会交给ViewGroup的onTouchEvent
这里onTouchEvent 返回 true表明，消费，事件到此结束不再传递


#### ViewGroup中的onInterceptTouchEvent返回true,onTouchEvent返回false(最后由Activty来消费)

		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP


事件从Activty的dispatchTouchEvent开始
调用ViewGroup的dispatchTouchEvent，此时这里返回super
会调用ViewGroup中的onInterceptTouchEvent拦截事件
onInterceptTouchEvent这里返回true，说明拦截
就会交给ViewGroup的onTouchEvent
这里onTouchEvent 返回 false表明，不消费，
事件会向上传递给Activity来调用onTouchEvent来消费


####ViewGroup中的onInterceptTouchEvent返回super



		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
		 MyImageView dispatchTouchEvent:   ACTION_DOWN
		  MyImageView     onTouchEvent:   ACTION_DOWN
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP


从上到下
Activity dispatchTouchEvent

ViewGroup  dispatchTouchEvent

ViewGroup  onInterceptTouchEvent

子View的 dispatchTouchEvent

子View的 onTouchEvent

ViewGroud的 onTouchEvent
Acitivty 的 onTouchEvent



#### 子View的dispatchTouchEvent返回false

		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
 		MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
 		MyImageView dispatchTouchEvent:   ACTION_DOWN
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP

前提ViewG的3个方法都返回super

从上到下
Activity dispatchTouchEvent

ViewGroup  dispatchTouchEvent

ViewGroup  onInterceptTouchEvent

子View dispatchTouchEvent（返回false直接回返到ViewGroup的onTouchEvent方法）
ViewGroup  onTouchEvent

Activity onTouchEvent

#### 子View的dispatchTouchEvent返回true

		
		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
		 MyImageView dispatchTouchEvent:   ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		MyrelativeLayout  dispatchTouchEvent:   ACTION_UP
		 MyrelativeLayout onInterceptTouchEvent  ACTION_UP
		 MyImageView dispatchTouchEvent:   ACTION_UP


从上到下
Activity dispatchTouchEvent

ViewGroup  dispatchTouchEvent

ViewGroup  onInterceptTouchEvent

子View dispatchTouchEvent（返回true直接消费此事件）


#### 子View的 onTouchEvent 返回true
前提
子类的dispatchTouchEvent返回super

		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
		 MyImageView dispatchTouchEvent:   ACTION_DOWN
		  MyImageView     onTouchEvent:   ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		MyrelativeLayout  dispatchTouchEvent:   ACTION_UP
		 MyrelativeLayout onInterceptTouchEvent  ACTION_UP
		 MyImageView dispatchTouchEvent:   ACTION_UP
		  MyImageView     onTouchEvent:   ACTION_UP
从上到下
Activity dispatchTouchEvent

ViewGroup  dispatchTouchEvent

ViewGroup  onInterceptTouchEvent

子View dispatchTouchEvent（super）

子View的onTouchEvent返回true消费这个事件，不再向上传递


#### 子View的 onTouchEvent 返回false
前提
子类的dispatchTouchEvent返回super

		dispatchTouchEvent: Activity    ACTION_DOWN
		MyrelativeLayout  dispatchTouchEvent:   ACTION_DOWN
		 MyrelativeLayout onInterceptTouchEvent  ACTION_DOWN
		 MyImageView dispatchTouchEvent:   ACTION_DOWN
		  MyImageView     onTouchEvent:   ACTION_DOWN
		MyrelativeLayout: onTouchEvent  ACTION_DOWN
		onTouchEvent: Activity    ACTION_DOWN
		dispatchTouchEvent: Activity    ACTION_UP
		onTouchEvent: Activity    ACTION_UP


从上到下
Activity dispatchTouchEvent

ViewGroup  dispatchTouchEvent

ViewGroup  onInterceptTouchEvent

子View dispatchTouchEvent（super）

子View的onTouchEvent返回false

ViewGroud的onTouchEvent

Acitivty的onTouchEvent



#### dispatchTouchEvent

如果 return true，事件会分发给当前 View 并由 dispatchTouchEvent 方法进行消费，同时事件会停止向下传递；
如果 return false，事件分发分为两种情况：
如果当前 View 获取的事件直接来自 Activity，则会将事件返回给 Activity 的 onTouchEvent 进行消费；
如果当前 View 获取的事件来自外层父控件，则会将事件返回给父 View 的  onTouchEvent 进行消费。
如果返回系统默认的 super.dispatchTouchEvent(ev)，事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法。



### 事件拦截：public boolean onInterceptTouchEvent(MotionEvent ev) 

在外层 View 的 dispatchTouchEvent(MotionEvent ev) 方法返回系统默认的 super.dispatchTouchEvent(ev) 情况下，事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法。onInterceptTouchEvent 的事件拦截逻辑如下：

如果 onInterceptTouchEvent 返回 true，则表示将事件进行拦截，并将拦截到的事件交由当前 View 的 onTouchEvent 进行处理；
如果 onInterceptTouchEvent 返回 false，则表示将事件放行，当前 View 上的事件会被传递到子 View 上，再由子 View 的 dispatchTouchEvent 来开始这个事件的分发；
如果 onInterceptTouchEvent 返回 super.onInterceptTouchEvent(ev)，，
如果有子View将事件传给子View由子View的dispatchTouchEvent来处理
如果没有子View并将拦截到的事件交由当前 View 的 onTouchEvent 进行处理。


#### 事件响应：public boolean onTouchEvent(MotionEvent ev)


在 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev) 并且 onInterceptTouchEvent 返回 true 或返回 super.onInterceptTouchEvent(ev) 的情况下 onTouchEvent 会被调用。onTouchEvent 的事件响应逻辑如下：

如果事件传递到当前 View 的 onTouchEvent 方法，而该方法返回了 false，那么这个事件会从当前 View 向上传递，并且都是由上层 View 的 onTouchEvent 来接收，如果传递到上面的 onTouchEvent 也返回 false，这个事件就会“消失”，而且接收不到下一次事件。
如果返回了 true 则会接收并消费该事件。
如果返回 super.onTouchEvent(ev) 默认处理事件的逻辑和返回 false 时相同


