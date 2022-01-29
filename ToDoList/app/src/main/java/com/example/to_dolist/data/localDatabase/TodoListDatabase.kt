package com.example.to_dolist.data.localDatabase


import androidx.room.*
import com.example.to_dolist.utils.TodoConverter

//@Database(entities = [TodoList::class], version = 2, exportSchema = false)
@Database(entities = [TodoListItem::class], version = 1)
@TypeConverters(TodoConverter::class)
abstract class TodoListDatabase : RoomDatabase() {

    abstract fun toDoDao(): TodoListDao

}

//@Database(entities = [TodoList::class], version = 1)
//@TypeConverters(TodoConverter::class)
//abstract class TodoListDatabase : RoomDatabase() {
//    abstract fun todoListDao(): TodoListDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: TodoListDatabase? = null
//        fun getDatabase(context: Context): TodoListDatabase {
//            if (INSTANCE == null) {
//                synchronized(this) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        TodoListDatabase::class.java,
//                        "todoListDB"
//                    ).addTypeConverter(TodoConverter()).build()
//
//                }
//            }
//            return INSTANCE!!
//        }
//
//    }
//}