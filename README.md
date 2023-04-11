# mini-chat

## Library
- com.google.code.gson:gson
- com.orhanobut:logger
- com.tencent:mmkv


## 基础聊天Sync
基本思路是推拉结合。推送需要重点考虑连接的稳定，拉取需要重点考虑Sync服务的性能。

sync服务
1. 基本功能（ISyncService）
   1. Sync服务状态变化，开始启动暂停停止等
   2. 分发消费网络层返回的SyncResult和Error（ISyncResultHandler）
   3. 管理队列Id（SeqId）
2. 启动时机
   1. 收到Push消息
   2. 业务启动
3. 缓存
   1. 在SyncService直接存入缓存，业务启动再去缓存文件读取并处理消息，但是会有缓存文件损坏的风险
   2. 多进程消息存入缓存文件，主进程业务启动再处理
4. 接口
   1. 多业务的SyncTask，put到一个队列，聚合成一个Task请求

Sync流程
   1. ISyncService启动
   2. 推送SyncTask到SyncTaskQueue
   3. SyncTaskQueue聚合任务
   4. Http请求后端
   5. 根据响应的Scene分发到对应ISyncService
   6. ISyncService将消息存入缓存SyncMsgCache
   7. 通知业务SDK或业务SDK启动，从SyncMsgCache中取出消息并处理