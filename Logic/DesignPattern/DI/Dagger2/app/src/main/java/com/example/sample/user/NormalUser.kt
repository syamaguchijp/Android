package com.example.sample.user

class NormalUser: User {
    override var appendix = "通常会員"
    override fun payBill(payment: Int): Int {
        return (payment * 1.10).toInt()
    }
}