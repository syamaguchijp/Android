package com.example.sample.user

class SpecialUser: User {
    override var appendix = "特別会員"
    override fun payBill(payment: Int): Int {
        return (payment * 0.90).toInt()
    }
}