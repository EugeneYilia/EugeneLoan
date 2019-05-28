package club.eugeneliu.trade.business;

public class Persistence {
    private Persistence(){}

    public static void generateTradeRecord(){//从消息队列中取出交易记录或者从别的地方拿到交易记录并且进行持久化存储

    }

    public static void checkRecordsWithBank(){//将记录发给银行，与银行进行对账

    }

    public static void generateWaterFlowRecord(){//生成流水记录

    }

    public static void checkBadLoan(){//检查坏账

    }
}
