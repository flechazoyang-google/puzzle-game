# 修改日志

## 2026-05-06

### 代码清理
- 删除无用的 `src/Main.java`
- 移除 `GameJFrame` 中未使用的 `x0`/`y0` 字段、`pictureList` 字段及 `ArrayList` import
- 清理 `LoginJFrame` 中的死代码、注释块和 debug 输出

### Bug 修复
- 修复拼图打乱算法：改为从终局出发做 200 次随机合法移动，保证拼图一定可解
- 修复 `LoginJFrame` 验证码校验逻辑：原代码未校验用户输入的验证码

### 跨平台兼容
- 所有图片路径从 Windows 反斜杠改为正斜杠，去掉 `puzzlegame/` 前缀

### 新增功能
- 实现 `RegisterJFrame` 注册功能：用户名/密码/确认密码/验证码表单，含完整校验逻辑
- 新增 `UserUtil` 工具类，用户数据持久化至 `data/users.txt`
- 注册时检查用户名是否已存在
