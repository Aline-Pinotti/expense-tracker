# Expense Tracker

Sistema de controle financeiro pessoal com suporte a transações, cartões de crédito, contas bancárias e notificações.

## Diagrama de Classes

```mermaid
classDiagram
    direction TB

    class User {
        - id: UUID
        - userName: String
        - email: String
        - password: String
        - createdAt: Instant
        - updatedAt: Instant
        + setAuthority(role: Role): Boolean
    }

    class Role {
        - id: UUID
        - authority: String
    }

    class RoleAuthority {
        <<Enum>>
        ROLE_USER
        ROLE_ADMIN
        ROLE_GUEST
    }

    class Notification {
        - id: UUID
        - subject: String
        - message: String
        - dateTime: LocalDate
        - readAt: Instant
        - createdAt: Instant
        - updatedAt: Instant
        + getUnread(user: User): List~Notification~
    }

    class Transaction {
        - id: UUID
        - description: String
        - amount: BigDecimal
        - date: LocalDate
        - isFixed: Boolean
        - dueDate: LocalDate
        - paidDate: LocalDate
        - inOut: String
        - numberOfInstallments: Integer
        - type: TransactionType
        - mainCategory: TransactionCategory
        - subCategories: Set~TransactionCategory~
        - paymentMethod: TransactionPaymentMethod
        - createdAt: Instant
        - updatedAt: Instant
        + exportTransactions(initialDate: LocalDate, finalDate: LocalDate): Json
        + importTransactions(): Boolean
        + generateNotification(): Notification
    }

    class TransactionHistory {
        %% TODO
    }

    class Installment {
        - id: UUID
        - description: String
        - amount: BigDecimal
        - paidDay: LocalDate
        - number: Integer
        - dueDate: LocalDate
        - createdAt: Instant
        - updatedAt: Instant
        + generateNext(transactionId: UUID): Installment
        + generateAll(): Boolean
        + generateNotification(): Notification
    }

    class Account {
        - id: UUID
        - name: String
        - code: String
        - agencyNo: String
        - balance: BigDecimal
        - limit: BigDecimal
        - specialLimit: BigDecimal
        - limitBudget: BigDecimal
        - cash: BigDecimal
        - createdAt: Instant
        - updatedAt: Instant
        + getAvailableLimit(date: LocalDate): BigDecimal
        + getAvailableBalance(date: LocalDate): BigDecimal
    }

    class Bank {
        - id: UUID
        - name: String
        - number: Integer
        - colorLabel: String
        - createdAt: Instant
        - updatedAt: Instant
    }

    class CreditCard {
        - id: UUID
        - name: String
        - number: Integer
        - limit: BigDecimal
        - closingDay: int
        - dueDay: int
        - expirationDate: YearMonth
        - isInternational: Boolean
        - createdAt: Instant
        - updatedAt: Instant
        + getAvailableLimit(date: LocalDate): BigDecimal
    }

    class CreditCardBill {
        - id: UUID
        - dueDate: LocalDate
        - closingDate: LocalDate
        - amount: BigDecimal
        - amountPaid: BigDecimal
        - paidDate: LocalDate
        - status: CreditCardBillStatus
        - createdAt: Instant
        - updatedAt: Instant
        + importBill(file: File): Boolean
        + generateBill(date: LocalDate): Boolean
        + getBill(dueDate: LocalDate): List~Transaction~
    }

    class CreditCardBillStatus {
        <<Enum>>
        OPEN
        CLOSED
        PAYED
    }

    class TransactionCategory {
        <<Enum>>
        HOUSING
        FOOD
        TRANSPORTATION
        ENTERTAINMENT
        UTILITIES
        HEALTHCARE
        EDUCATION
        SALARY
        CASHBACK
        OTHER
    }

    class TransactionPaymentMethod {
        <<Enum>>
        CASH
        CREDIT_CARD
        DEBIT_CARD
        BANK_TRANSFER
        BANK_SLIP
        MOBILE_PAYMENT
        PIX
        OTHER
    }

    class TransactionType {
        <<Enum>>
        DEPOSIT
        EXPENSE
        INVESTMENT
        WITHDRAW
    }

    %% Relationships
    User "*" -- "1..*" Role : roles
    User "1" -- "0..*" Notification : notifications
    User "1..*" -- "*" Account


    Transaction "1" -- "*" TransactionHistory
    Transaction "1" -- "*" Installment : installments
    Transaction "*" -- "0..1" Account : transactions
    Transaction "*" -- "0..1" CreditCardBill : transactions


    CreditCardBill "*" -- "1" CreditCard : bills

    CreditCard "0..*" -- "0..1" Account

    Account "*" -- "1" Bank
    User "1" -- "*" Transaction: transactions
```

## Entidades

| Entidade             | Descrição                                                     |
| -------------------- | ------------------------------------------------------------- |
| `User`               | Usuário do sistema com controle de papéis e notificações      |
| `Role`               | Papel/permissão associado ao usuário                          |
| `Notification`       | Notificações geradas por transações e parcelas                |
| `Transaction`        | Transação financeira (despesa, receita, investimento)         |
| `Installment`        | Parcelas vinculadas a uma transação                           |
| `Account`            | Conta bancária com saldos e limites                           |
| `Bank`               | Banco associado a contas e cartões                            |
| `CreditCard`         | Cartão de crédito com limite e datas de fechamento/vencimento |
| `CreditCardBill`     | Fatura do cartão de crédito                                   |
| `TransactionHistory` | Histórico de alterações de uma transação _(a implementar)_    |

## Enumerações

| Enum                       | Valores                                                                                                                     |
| -------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| `TransactionType`          | `DEPOSIT`, `EXPENSE`, `INVESTMENT`, `WITHDRAW`                                                                              |
| `TransactionCategory`      | `HOUSING`, `FOOD`, `TRANSPORTATION`, `ENTERTAINMENT`, `UTILITIES`, `HEALTHCARE`, `EDUCATION`, `SALARY`, `CASHBACK`, `OTHER` |
| `TransactionPaymentMethod` | `CASH`, `CREDIT_CARD`, `DEBIT_CARD`, `BANK_TRANSFER`, `BANK_SLIP`, `MOBILE_PAYMENT`, `PIX`, `OTHER`                         |
| `CreditCardBillStatus`     | `OPEN`, `CLOSED`, `PAYED`                                                                                                   |
| `RoleAuthority`            | `ROLE_USER`, `ROLE_ADMIN`, `ROLE_GUEST`                                                                                     |
