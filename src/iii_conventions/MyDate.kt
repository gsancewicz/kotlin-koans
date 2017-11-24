package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    operator override fun compareTo(other: MyDate) : Int {
        return if(year != other.year) year - other.year;
        else if(month != other.month) month - other.month;
        else dayOfMonth - other.dayOfMonth;
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

data class TimeIntervals(val interval: TimeInterval, val number: Int);

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {


    operator fun contains(item: MyDate): Boolean {
        return item >= start && item <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var next: MyDate = start

            override fun hasNext(): Boolean {
                return next < endInclusive.nextDay()
            }

            override fun next(): MyDate {
                val result = next
                next = next.nextDay()
                return result
            }
        }
    }
}
