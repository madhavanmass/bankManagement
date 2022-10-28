package bank.hdfc.pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.TimerTask;

import bank.hdfc.dao.BankDao;

class UpdateDates implements Serializable{
	private static final long serialVersionUID = 1L;
	LocalDate dailyUpdate;
	public LocalDate monthlyUpdate;
	public void updateDailyDate() {
		this.dailyUpdate= LocalDate.now() ;
	}
	
}
public class BankRoutine extends TimerTask  {
	BankDao bankDao=new BankDao();
	public void run() {
		try {
            ObjectInputStream inputfromfile=new ObjectInputStream(new FileInputStream("BankTask.txt"));  
            UpdateDates dates=(UpdateDates)inputfromfile.readObject();
            inputfromfile.close();
            Period Difference=Period.between(dates.dailyUpdate, LocalDate.now());
            if(Difference.getDays()>=1){
                dates.updateDailyDate();
                bankDao.resetTransfers();
                bankDao.updateSavingAccount();
                bankDao.updateDeposits();
                bankDao.updateLoanAccounts();
                bankDao.checkPenality();
            }
            ObjectOutputStream writefile=new ObjectOutputStream(new FileOutputStream("BankTask.txt"));
            writefile.writeObject(dates);    
            writefile.flush();
            writefile.close();
            
        } catch (Exception e) {
           System.out.println("ERROR IN GETTING DATES");
           e.printStackTrace();
        }
	}

}
