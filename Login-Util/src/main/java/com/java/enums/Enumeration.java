package com.java.enums;

public class Enumeration {

    public interface GenericEnum {
        long getId();
    }

    public enum Confirmation implements GenericEnum {
        YES,
        NO;


        public long getId() {

            return this.ordinal();
        }

    }

    public enum UserAccountStatus implements GenericEnum {
        INACTIVE(),
        ACTIVE(),
        SUSPENDED(),
        VERIFICATION_PENDING();

        public long getId() {

            return this.ordinal();
        }
    }

    public enum PassowordResetStatus implements GenericEnum {

        EXPIRED(),
        NEW(),
        PROCESSED(),
        UNUSED(),
        TOKEN_VERIFIED();


        public long getId() {
            return this.ordinal();
        }

    }

    public enum UserTokenType implements GenericEnum {
        VERIFICATION,
        PASSWORD_RESET;


        public long getId() {
            return this.ordinal();
        }
    }

    public enum ErrorCode implements GenericEnum{

        FORBIDDEN_403("403 Forbidden"),
        UNAUTHORIZED_401("401 UnAuthorized."),
        INTERNAL_SERVER_ERROR_500("500 Internal Server Error"),
        BAD_REQEST_400("400 Bad Request");

        String errorMessage;

        ErrorCode(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public long getId() {
            return this.ordinal();
        }
    }


    public enum ConfigKeys implements GenericEnum{

        TEMP_PASSWORD,
        APP_BASE_URL,
        RESET_LINK,
        EMAIL_VERIFY_LINK;

        public long getId() {
            return this.ordinal();
        }
    }


    public enum EmailEventStatus implements GenericEnum{

        PENDING,
        SENT,
        FAILED;

        public long getId() {
            return this.ordinal();
        }
    }

    public enum EmailSubjects implements GenericEnum {

        INVITATION_EMAIL(" You are invited to Login CMS, Please create your Account"),
        VERIFICATION_EMAIL("Login - Verify your Email"),
        PASSWORD_RESET_EMAIL("Login - Password Reset Request");

        EmailSubjects(String s) {

            this.label = s;
        }

        String label;
        public long getId() {
            return this.ordinal();
        }

        public String getLabel() { return this.label;}
    }

    public enum CasinoStatus implements GenericEnum {
        ACTIVE(),
        CLOSE(),
        TEMPORARILY_SUSPENDED();


        public long getId() {

            return this.ordinal();
        }
    }

    public enum ContactType implements GenericEnum {

        OWNER,
        MANAGER,
        ONLINE_SUPPORT,
        TECH_SUPPORT,
        PAYMENTS,
        INVOICING;


        public long getId() {

            return this.ordinal();
        }
    }

    public enum ContractStatus implements GenericEnum {
        ACTIVE(),
        CLOSE(),
        TEMPORARILY_SUSPENDED();

        public long getId() {

            return this.ordinal();
        }
    }

    public enum TransactionType implements GenericEnum {

        INCOME,
        EXPENSE,
        INITIAL,
        TRANSFER;

        public long getId() {

            return this.ordinal();
        }

    }

    public enum ResultType implements GenericEnum {

        FULL,
        LISTING,
        SELECTION;

        public long getId() {

            return this.ordinal();
        }

    }
}
