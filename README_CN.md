# compose-floating-window

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

基于Jetpack Compose的全局悬浮窗框架

## 效果预览

## 特性

- 使用Compose代码描述悬浮窗界面
- ViewModel支持
- 可拖拽悬浮窗支持
- 基于Application Context的对话框组件

## 基本使用

### 导入依赖

- 如果Gradle版本小于7.0，在应用的`build.gradle`中，添加Jitpack仓库

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

- 如果Gradle版本大于等于7.0，在 settings.gradle 文件中加入
```groovy
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

- 添加ComposeFloatingWindow依赖
```groovy
dependencies {
    implementation "com.github.only52607:ComposeFloatingWindow:1.0"
}
```

### 增加悬浮窗权限

在`AndroidManifest.xml`中添加
```xml
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```

### 创建悬浮窗并显示

```kotlin
val floatingWindow = ComposeFloatingWindow(applicationContext)
floatingWindow.setContent {
    FloatingActionButton(
        modifier = Modifier.dragFloatingWindow(),
        onClick = {
            Log.i("")
        }) {
        Icon(Icons.Filled.Call, "Call")
    }
}
floatingWindow.show()
```

> 查看[示例程序](https://github.com/only52607/compose-floating-window/tree/master/app)，了解详细用法。

## 高级用法

### 创建可拖拽的悬浮窗

在需要拖拽的组件上使用`Modifier.dragFloatingWindow()`修饰符，示例：

```kotlin
FloatingActionButton(
    modifier = Modifier.dragFloatingWindow()
) {
    Icon(Icons.Filled.Call, "Call")
}
```

### 获取当前ComposeFloatingWindow实例

使用`LocalComposeFloatingWindow`获取，示例：

```kotlin
val floatingWindow = LocalComposeFloatingWindow.current
```

### 显示对话框

当悬浮窗的Context为Application时，在悬浮窗的Compose界面中使用`AlertDialog`和`Dialog`会出现token is null异常，这时可使用`SystemAlertDialog`或`SystemDialog`组件，用法与自带的`AlertDialog`和`Dialog`一致。

示例：
```kotlin
SystemAlertDialog(
    onDismissRequest = { showDialog = false },
    confirmButton = {
        TextButton(onClick = { showDialog = false }) {
            Text(text = "OK")
        }
    },
    text = {
        Text(text = "This is a system dialog")
    }
)
```

### 使用ViewModel

通过调用 viewModel() 函数，从任何可组合项访问 ViewModel。

```kotlin
class MyViewModel : ViewModel() { /*...*/ }

@Composable
fun MyScreen(
    viewModel: MyViewModel = viewModel()
) {
    // use viewModel here
}
```

> 详情请参照[Android文档](https://developer.android.com/jetpack/compose/libraries#viewmodel)

## License

Apache 2.0 License