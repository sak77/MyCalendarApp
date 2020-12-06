package com.saket.mycalendarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saket.mycalendarapp.databinding.ActivityMainBinding

import java.time.Month

class MainActivity : AppCompatActivity() {
    /**
     * Days of the week: Is it a holiday
     * Find No. of weeks in a given month in a year.
     * Months of the year. Days in a month.
     * Year - Is it a leap year.
     * Get a day corresponding to a date.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnDaysOfWeek.setOnClickListener {
            showDaysOfTheWeek()
        }

        binding.btnHolidays.setOnClickListener {
            showHolidays()
        }

        binding.btnMonths.setOnClickListener {
            showMonths()
        }

        binding.btnDaysinAMonth.setOnClickListener {
            showDaysInMonth(isLeap = true)
        }

        setContentView(binding.root)
    }


    private fun showDaysOfTheWeek() {
        DaysOfTheWeek.values().forEach { println("Day of the week : $it") }
    }

    private fun showHolidays() {
        DaysOfTheWeek.values().filter { it.isHoliday() }.forEach { println("Holidays on : $it") }
    }

    private fun showMonths() {
        MonthsOfTheYear.values().forEach {
            println("Month of the year : $it")
        }
    }

    private fun showDaysInMonth(isLeap: Boolean) {
        MonthsOfTheYear.values().forEach {
            //println("Days in month: $it are ${it.noOfDays(isLeap)}")
            println("Weeks in month: $it are ${it.noOfWeeks(isLeap)}")
        }
    }

    /*
    Enum class is like a regular class with methods and properties. Except that it contains
    public static final instances of itself. So in below class, SUNDAY, MONDAY, TUESDAY etc. are
    all public static final instances of class DaysOfTheWeek. For SUNDAY and SATURDAY, i override
    isHoliday to return true.
     */
    enum class DaysOfTheWeek {
        SUNDAY {
            /*
            override keyword tells that we are overriding an open method here.
             */
            override fun isHoliday(): Boolean {
                return true
            }
        },
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY {
            override fun isHoliday(): Boolean {
                return true
            }
        };

        /*By default all methods and classes in kotlin are final. So they cannot be overriden.
        Hence we use the 'open' keyword to make a method/class open to being overriden (or inheritable).
        */
        open fun isHoliday(): Boolean {
            return false
        }
    }


    /*
    Looking at implementation of java.time.Month enum, It seems that better than overriding values
    for individual enums, the other way is to define the logic inside the parent class and maybe use
    switch/when statement to provide values based on current enum.
     */
    enum class MonthsOfTheYear {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

        fun noOfDays(leapYear: Boolean): Int {
            return when (this) {
                JANUARY -> 31
                FEBRUARY -> if (leapYear) 29 else 28
                MARCH -> 31
                APRIL -> 30
                MAY -> 31
                JUNE -> 30
                JULY -> 31
                AUGUST -> 31
                SEPTEMBER -> 30
                OCTOBER -> 31
                NOVEMBER -> 30
                DECEMBER -> 31
            }
        }

        fun noOfWeeks(leapYear: Boolean) = noOfDays(leapYear) / 7
    }
}