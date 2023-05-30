package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    DataHelper.InfoCard card1 = DataHelper.getFirstCardNumber();
    DataHelper.InfoCard card2 = DataHelper.getSecondCardNumber();
    @Test
    void shouldTransferMoneyBetweenCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        int amount = 100;
        int firstBalance = DashboardPage.getFirstCardBalance();
        int secondBalance = DashboardPage.getSecondCardBalance();
        DashboardPage dashboardPage = new DashboardPage();
        var transfer = dashboardPage.choseCard(card1);
        transfer.upCard(amount,card2);
        assertEquals(firstBalance + amount, dashboardPage.getFirstCardBalance());
        assertEquals(secondBalance - amount, dashboardPage.getSecondCardBalance());
    }
    @Test
    void shouldTransferMoneyBetweenCards2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        int amount = 1000;
        int firstBalance = DashboardPage.getFirstCardBalance();
        int secondBalance = DashboardPage.getSecondCardBalance();
        DashboardPage dashboardPage = new DashboardPage();
        var transfer = dashboardPage.choseCard(card2);
        transfer.upCard(amount,card1);
        assertEquals(firstBalance - amount, dashboardPage.getFirstCardBalance());
        assertEquals(secondBalance + amount, dashboardPage.getSecondCardBalance());
    }

}
