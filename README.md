# AndroidCustomView
Android自定义View备忘

自定义View：一般只需要在重写onDraw的时候考虑自己的padding

自定义ViewGroup:一般需要在onMeasure时考虑自己的padding和child的margin，一般measure child的时候不考虑child的margin，但是记录margin，在设置自己的dim时考虑总的margin；
在onLayout的时候需要同时考虑自己的padding和child的margin;

wrap content的处理：根据传入的spec，如果是at_most,那么根据情况取值

注意view measure时传入的spec是规定自己的，如果是ViewGroup，那么首先应当为child生成spec，再调用child的measure，这个过程写在了view group的measureChild函数中
