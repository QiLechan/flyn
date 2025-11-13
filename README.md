# 法粒援农

一个使用 Kotlin 和 Jetpack Compose 构建的现代化 Android 应用。

## 概述

法粒援农是一个 Android 应用程序，使用 Kotlin 和 Jetpack Compose 进行现代 Android 开发的最佳实践。

## 功能开发进度

- ✅Jetpack Compose 实现现代化 UI 开发
- ✅用户登录与注册系统
- 通知推送功能
- UI美化设计
- APP内容添加
- 安全性提升

## 前置要求

- Android Studio（推荐最新版本）
- Android SDK API 级别 36
- Java 11 或更高版本
- 至少 Android API 级别 31（最低 SDK）用于安装

## 快速开始

1. 克隆仓库：
   ```bash
   git clone https://github.com/QiLechan/flyn.git
   ```

2. 在 Android Studio 中打开项目：
   - 选择"打开现有的 Android Studio 项目"
   - 导航到克隆的目录
   - 选择项目根文件夹

3. 构建项目：
   - Android Studio 将自动同步 Gradle 文件
   - 等待同步完成

4. 运行应用程序：
   - 连接 Android 设备或启动模拟器
   - 点击 Android Studio 中的"运行"按钮

## 项目结构

```
flyn/
├── app/                    # 主应用模块
│   ├── src/main/
│   │   ├── java/          # Kotlin 源文件
│   │   ├── res/           # 资源文件（图像、布局等）
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts   # 模块构建配置
├── build.gradle.kts       # 项目级构建配置
├── settings.gradle.kts    # 项目设置
└── README.md              # 此文件
```

## 权限

应用程序需要以下权限：
- `INTERNET`: 执行网络操作
- `ACCESS_NETWORK_STATE`: 检查网络连接状态

## 构建配置

项目配置包括：
- 编译 SDK: 36
- 最低 SDK: 31
- 目标 SDK: 36
- Kotlin 版本: 兼容 Java 11
- Compose BOM: AndroidX Compose 版本清单

## 依赖项

主要依赖项包括：
- AndroidX Core KTX
- Lifecycle Runtime KTX
- Activity Compose
- AndroidX Compose BOM
- Material Components
- Navigation Compose
- OkHttp
- AndroidX Security Crypto

## 贡献

1. Fork 仓库
2. 创建功能分支 (`git checkout -b feature/awesome-feature`)
3. 提交更改 (`git commit -m 'Add awesome feature'`)
4. 推送到分支 (`git push origin feature/awesome-feature`)
5. 开启 Pull Request

## 联系方式

如有问题或需要支持，请在仓库中提交 issue。