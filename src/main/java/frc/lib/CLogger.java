package frc.lib;

public class CLogger {
    // Logger Mode Enum
    public enum cLoggerMode {
        COMPETITION,
        PRACTICE,
        TESTING,
        DEVELOPMENT
    }

    // The log type
    public enum cLogType {
        MajorError,
        MinorError,
        Warning,
        Debug,
        Undefined
    }

    // Should Logs
    private boolean sl_majorErrors;
    private boolean sl_minorErrors;
    private boolean sl_warnings;
    private boolean sl_debugPrints;
    private boolean sl_undefinedPrints;
    
    /**
     * Constructor 
     * @param mode Logger Mode
     */
    public CLogger(cLoggerMode mode) {
        setup(mode);
    }

    /**
     * Catch for constructor missing params
     */
    public CLogger() {
        setup(cLoggerMode.DEVELOPMENT);
    }

    /**
     * Initial setup called by the constructor
     * @param mode Passed by constructor
     */
    private void setup(cLoggerMode mode) {
        switch (mode) {
            case COMPETITION:
                sl_majorErrors     = false;
                sl_minorErrors     = false;
                sl_warnings        = false;
                sl_debugPrints     = false;
                sl_undefinedPrints = false;
                break;
            case PRACTICE:
                sl_majorErrors     = true;
                sl_minorErrors     = false;
                sl_warnings        = false;
                sl_debugPrints     = false;
                sl_undefinedPrints = false;
                break;
            case TESTING:
                sl_majorErrors     = true;
                sl_minorErrors     = true;
                sl_warnings        = true;
                sl_debugPrints     = false;
                sl_undefinedPrints = false;
                break;
            case DEVELOPMENT:
                sl_majorErrors     = true;
                sl_minorErrors     = true;
                sl_warnings        = true;
                sl_debugPrints     = true;
                sl_undefinedPrints = true;
                break;
            default:
                break;
        }
    }


    /**
     * This is the public logging manager
     * @param value Value to log
     * @param type What type of log is this
     */
    public void log(String value, cLogType type) {
        switch (type) {
            case MajorError:
                if (sl_majorErrors) {
                    System.out.print(value);
                }
                break;
            case MinorError:
                if (sl_minorErrors) {
                    System.out.print(value);
                }
                break;
            case Warning:
                if (sl_warnings) {
                    System.out.print(value);
                }
                break;
            case Debug:
                if (sl_debugPrints) {
                    System.out.print(value);
                }
                break;
            case Undefined:
                if (sl_undefinedPrints) {
                    System.out.print(value);
                }
                break;
            default:
                if (sl_undefinedPrints) {
                    System.out.print(value);
                }
                break;
        }
    }

    /**
     * A catch for an undefined log type
     * @param value Value to log
     */
    public void log(String value) {
        log(value, cLogType.Undefined);
    }

    /**
     * The replicative method of log, but instead uses println
     * @param value The value to log
     * @param type The type of value to log
     */
    public void logln(String value, cLogType type) {
        switch (type) {
            case MajorError:
                if (sl_majorErrors) {
                    System.out.println(value);
                }
                break;
            case MinorError:
                if (sl_minorErrors) {
                    System.out.println(value);
                }
                break;
            case Warning:
                if (sl_warnings) {
                    System.out.println(value);
                }
                break;
            case Debug:
                if (sl_debugPrints) {
                    System.out.println(value);
                }
                break;
            case Undefined:
                if (sl_undefinedPrints) {
                    System.out.println(value);
                }
                break;
            default:
                if (sl_undefinedPrints) {
                    System.out.println(value);
                }
                break;
        }
    }

    /**
     * A catch for an undefined logType for logln
     * @param value The value to log
     */
    public void logln(String value) {
        logln(value, cLogType.Undefined);
    }

    /**
     * Logging a double
     * @param value
     * @param type
     */
    public void logln(Double value, cLogType type) {
        this.logln(value.toString());
    }

    /**
     * A catch for an undefined LogType
     * @param value
     */
    public void logln(Double value) {
        logln(value, cLogType.Undefined);
    }

    /**
     * Bypasses the logging manager and goes straight to sysout
     * @param value The value to log
     */
    public void logBypass(Object value) {
        System.out.print(value);
    }

    /**
     * Bypasses the logging manager and does a println
     * @param value The value to log
     */
    public void logBypassln(String value) {
        System.out.println(value);
    }

    public void updateLogLevel(cLoggerMode cLogMode) {
        setup(cLogMode);
    }
}