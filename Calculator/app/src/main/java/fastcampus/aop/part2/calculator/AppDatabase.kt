package fastcampus.aop.part2.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import fastcampus.aop.part2.calculator.dao.HistoryDAO
import fastcampus.aop.part2.calculator.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDAO(): HistoryDAO
}