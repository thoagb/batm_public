/*************************************************************************************
 * Copyright (C) 2014-2024 GENERAL BYTES s.r.o. All rights reserved.
 *
 * This software may be distributed and modified under the terms of the GNU
 * General Public License version 2 (GPL2) as published by the Free Software
 * Foundation and appearing in the file GPL2.TXT included in the packaging of
 * this file. Please note that GPL2 Section 2[b] requires that all works based
 * on this software must also be made publicly available under the terms of
 * the GPL2 ("Copyleft").
 *
 * Contact information
 * -------------------
 *
 * GENERAL BYTES s.r.o.
 * Web      :  http://www.generalbytes.com
 *
 ************************************************************************************/
package com.generalbytes.batm.server.extensions;

import com.generalbytes.batm.server.extensions.questionnaire.QuestionnaireResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ITransactionRequest {

    //Types of transactions
    int TYPE_BUY_CRYPTO = 0;
    int TYPE_SELL_CRYPTO = 1;
    int TYPE_WITHDRAW_CASH = 2;
    int TYPE_CASHBACK = 3;
    int TYPE_ORDER_CRYPTO = 4;
    int TYPE_DEPOSIT_CASH = 5;

    /**
     * Server time of the transaction
     * @return
     */
    Date getServerTime();

    /**
     * Terminal time of the transaction - calculated from the terminal location timezone (you may have terminals across multiple time zones)
     * @return
     */
    Date getTerminalTime();

    /**
     * Returns type of the transaction
     * {@value #TYPE_BUY_CRYPTO}
     * {@value #TYPE_SELL_CRYPTO}
     * {@value #TYPE_WITHDRAW_CASH}
     * @return
     */
    int getType();

    /**
     * Returns serial number of the terminal where the transaction was created
     * @return
     */
    String getTerminalSerialNumber();

    /**
     * Returns unique (in server scope) transaction id. It is generated by server.
     * @return
     */
    String getRemoteTransactionId();

    /**
     * Returns transaction id generated locally by terminal to perform request on server.
     * Don't use it if you don't have to. It is used only for time before server assignes remote transaction id to a transaction.
     * @return
     */
    String getLocalTransactionId();


    /**
     * Fiat amount
     * @return
     */
    BigDecimal getCashAmount();

    /**
     * Fiat currency code (USD, EUR etc)
     * @return
     */
    String getCashCurrency();

    /**
     * Amount of coins - crypto-currency
     * @return
     */
    BigDecimal getCryptoAmount();

    /**
     * Cryptocurrency code (BTC, ETH etc)
     * @return
     */
    String getCryptoCurrency();

    /**
     * Destination address where the coins were sent to or where the coins were supposed to be sent to.
     * null for LBTC (Lightning network)
     * @return
     */
    String getCryptoAddress();

    /**
     * Returns what was the fixed transaction fee in fiat currency used for the transaction
     * @return
     */
    BigDecimal getFixedTransactionFee();

    /**
     * Server internal identity id of person performing the transaction
     * @return
     */
    String getIdentityPublicId();

    /**
     * In {@value TYPE_WITHDRAW_CASH} type of transaction this method returns
     * remote transaction id of related {@value TYPE_SELL_CRYPTO} sell transaction.
     * @return
     */
    String getRelatedRemoteTransactionId();

    /**
     * Contains customer phonenumber that was used during transaction
     * @return
     */
    String getCellPhoneUsed();

    /**
     * Indicates that transaction was automatically finished by server.
     * Ie. terminal reported that 100 USD was inserted into the machine and then terminal went offline.
     * Server automatically finished transaction and sent coins to customer.
     * @return
     */
    boolean isAutoexecuted();


    /**
     * Returns discount code used when performing transaction
     * @return
     */
    String getDiscountCode();

    /**
     * Returns discount percentage of fee
     * @return
     */
    BigDecimal getFeeDiscount();

    /**
     * Amount of discount in cryptocurrency
     * @return
     */
    BigDecimal getCryptoDiscountAmount();

    /**
     * @return
     */
    BigDecimal getDiscountQuotient();

    /**
     * Returns questionnaire results if any questionnaire has been activated.
     *
     * @return List of {@link QuestionnaireResult}. Can be null.
     */
    List<QuestionnaireResult> getQuestionnaireResults();

    /**
     * Error message displayed to the customer.
     *
     * @return Error message.
     */
    String getErrorMessage();


    /**
     * Error message displayed to the customer.
     */
    void setErrorMessage(String errorMessage);

}
