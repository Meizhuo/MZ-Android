### Activity
1. 不要直接MainAcitivity,直接Main
2. 其布局文件文件应为acty_main ,前缀为acty_

### 布局中的style明明
1. 若是button的样式，直接button_XXX__yy 来描述
   TextView 的样式  textview_xx_y
   Linearlayout也类似

### Dialog
LoadingDialog -> 其布局文件应为dlg_xxx

### ListView 中


### 布局中的id
若是button,则命名为btn_XXX，例如如果是确认的按钮，明明为btn_confirm
LinerLayyout -> lily_XXX
RelateLayout -> rely_xx
TextView ->tv_xxx
ImageView -> iv_
若是自定义的控件，例如AutoScrollViewPager 保留原样即可

### 图片命名
abc.img
->ic_xx_y.img
例子：
xx 若是pressed 状态的button,ic_btn_pressed,若有颜色则ic_btn_pressed_blue.img

### 颜色明明color.xml
加上前缀mz_  
如红色:mz_red


