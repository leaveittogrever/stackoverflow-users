package com.example.stackoverflowusers

import androidx.room.Room
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
     fun insertUsers() {
        val user = User(
            123,
            "Paul G",
            "http://image.com",
            BadgeCount(1, 10, 50)
        )

        runBlocking {
            userDao.insertAll(listOf(user))
            val users = userDao.getAll()
            Assert.assertEquals(users.size, 1)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertMultipleUsers() {
        val user = User(
            123,
            "Paul G",
            "http://image.com",
            BadgeCount(1, 10, 50)
        )
        val user2 = User(
            12,
            "Johnny",
            "http://image.com",
            BadgeCount(1, 10, 50)
        )
        val user3 = User(
            124,
            "Steve",
            "http://image.com",
            BadgeCount(1, 10, 5)
        )

        runBlocking {
            userDao.insertAll(listOf(user, user2, user3))
            val users = userDao.getAll()
            Assert.assertEquals(users.size, 3)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteUsers() {
        val user = User(
            123,
            "Paul G",
            "http://image.com",
            BadgeCount(1, 10, 50)
        )
        val user2 = User(
            12,
            "Johnny",
            "http://image.com",
            BadgeCount(1, 10, 50)
        )
        val user3 = User(
            124,
            "Steve",
            "http://image.com",
            BadgeCount(1, 10, 5)
        )

        runBlocking {
            userDao.insertAll(listOf(user, user2, user3))
            var users = userDao.getAll()
            Assert.assertEquals(users.size, 3)
            userDao.deleteAll()
            users = userDao.getAll()
            Assert.assertEquals(users.size, 0)
        }
    }


}