package com.example.lab1_navigation.model

class AccountInfo {
    fun fetchAccountInfo(): Account {
        return Account("dholub", "Denys", "Holub",
            "Developer", "Cherkassy", 49.44F,  32.06F)
    }
}