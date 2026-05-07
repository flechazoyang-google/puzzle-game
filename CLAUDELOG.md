# Claude Code 操作日志

## 2026-05-07

### 清理测试文件
删除了以下临时测试文件（资源加载测试）：
- `TestRes.java`、`TestRes.class`（根目录）
- `TestResource.java`、`TestResource.class`（根目录）
- `src/TestRes.java`
- `out/TestRes.class`、`out/TestResource.class`
- `test.jar`

### 更新 .gitignore
添加了以下忽略规则：
- `*.class` - 编译的类文件
- `*.jar`、`*.zip` - 打包文件
- `out/` - 编译输出目录
- `*.iml`、`.idea/` - IDE 配置文件
- `.claude/` - Claude Code 目录
- `PuzzleGame/` - 解压的游戏目录
