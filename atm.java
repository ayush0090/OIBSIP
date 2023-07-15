import java.util.Dictionary;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Scanner;

public class atm
{
    public static void main(String a[])
    {
        Scanner take=new Scanner(System.in);
        usercan can2=new usercan();
        usercan customer[]=new usercan[100];
        trans t=new trans();
        int c=0;
        System.out.println("******   Automated teller machine  ********");
        
        while(true)
        {
            System.out.println("1)Register \t2)Transaction History\n3)Withdrawal\t4)Quick Deposit\n5)Fund Transfer\t6)Check Balance\n\t7)Exit");
            System.out.print("\n Select Service : ");
            int chah=take.nextInt();
            switch(chah)
            {
                case 1:
                        usercan can=new usercan();
                        can.registration();
                        customer[c]=can;
                        
                        c++;
                        break;
                case 2:
                        int u=can2.for_transaction(customer);
                        if(u!=1000)
                        {t.transaction_(customer[u],u,1);}
                        else{System.out.println("---------Not Registered. Please Register----------\n");}
                        break;
                case 3: 
                        int u2=can2.for_transaction(customer);
                        if(u2!=1000)
                        {t.transaction_(customer[u2],u2,2);}
                        else{System.out.println("---------Not Registered. Please Register----------\n");}
                        break;
                case 4:
                        int u3=can2.for_transaction(customer);
                        if(u3!=1000)
                        {t.transaction_(customer[u3],u3,3);}
                        else{System.out.println("---------Not Registered. Please Register----------\n");}
                        break;
                case 5:
                        int p[]=can2.for_transfer(customer);
                        if(p[0]!=1000 && p[1]!=1000)
                        {
                            t.transaction_1(customer[0],customer[1],p[0],p[1]); }
                        else{System.out.println("---------Please Enter valid details----------\n");}
                        break;
                case 6:
                        usercan Obj_1=new usercan();
                        Obj_1.details(customer,Obj_1.check(customer));
                        break;  
                case 7:
                        System.exit(0);
                        break;
                default:
                        System.out.println("----Please Select Valid Service----");  
                        break;   
            }
        }
    }
}


class usercan
{
    Scanner take=new Scanner(System.in);
    public  String name;
    public  long pswd,RemBal,withMoney,Acc,deposit,phNo;
    public  void registration()
    {
        try
        {
        System.out.print("Enter your name : ");
        name=take.nextLine();
         System.out.print("Enter Acc number: ");
        Acc=take.nextLong();
         System.out.print("Enter 10-digit phone number: ");
        phNo=take.nextLong();
        take.nextLine();
         System.out.print("Enter pin: ");
        pswd=take.nextLong();
        RemBal=0;
        System.out.print("\n-----Registration is completed-----\n");
        }
        catch(Exception e){}

    }

    public void details(usercan a[],int j)
    {
        
         if( j!=1000)
        {System.out.print("----------Details----------\n"+"Name : "+a[j].name+"\n"+"Phone Number : "+a[j].phNo+"\n"+"Account Number : "+a[j].Acc+"\n"+"Balance Amount : "+a[j].RemBal+"\n");}
        else{System.out.println("--------Enter valid Account and Pin Number---------");}
    }

    public int check(usercan a[])
    {
        System.out.print("Enter Acc number : ");
        long acc=take.nextLong();
        System.out.print("Enter Pin: ");
        long pin=take.nextLong();
        int j,f=0;
        try
        {
           for(j=0;j<a.length;j++)
           {
            if((a[j].Acc==acc) && a[j].pswd==pin){f=1;break;}
           }     
           if(f==1){return j;}
            else{return 1000;}
        }
        catch(Exception e)
        {return 1000;}
    }

    public int for_transaction(usercan a[])
    {
        System.out.print("Enter Acc number : ");
        long acc=take.nextLong();
        System.out.print("Enter Pin: ");
        long pin=take.nextLong();
        int j,f=0;
        try
        {
           for(j=0;j<a.length;j++)
           {
            if((a[j].Acc==acc) && a[j].pswd==pin){f=1;break;}
           }     
           if(f==1)
            {
                return j;
            }
           else{return 1000;}
        }
        catch(Exception e)
        {return 1000;}
    }
    public int[] for_transfer(usercan a[])
    {
        int x=2,p[]=new int[2],i=0;
        while(x!=0)
        {
            System.out.println((x==2)?"---Enter Sender's Acc details ---":"---Enter Recipients details---");
            System.out.print("Acc number : ");
            long acc=take.nextLong();
            System.out.print("Enter Pin : ");
            long pin=take.nextLong();
            int j,f=0;
            try
            {
                for(j=0;j<a.length;j++)
                {
                    if((a[j].Acc==acc) && a[j].pswd==pin){f=1;break;}
                }     
                if (f==1){p[i]=j;}
                else{p[i]=1000;}
            }
            catch(Exception e){}
            x--;
        }
        return p;
    }

}

class trans
{
    
    Dictionary<Integer,String[]> dict= new Hashtable<>();
    String x[][]=new String[100][100],y;
    int j;
    void transaction_(usercan u,int customer,int i)
    {
        //trans details
        
            if(i==1)
            {
            System.out.println("------------Transaction History--------------\n");
            String y[]=(dict.get(customer));
            try
            {  
                for(int l=0;y[l]!=null;l++)
                {System.out.println(y[l]);}
            }
            catch(Exception e)
            {}
            }
            //withdraw
            else if(i==2)
            {
                y=new withdraw().withdraw_(u,customer);
                if(y!="")
                {
                    for( j=0;x[customer][j]!=null;j++){}
                    x[customer][j]=y;
                    dict.put(customer,x[customer]);
                }
            }

            //deposit
            else if(i==3)
            {
                y=new deposit().deposit_(u,customer);
                for( j=0;x[customer][j]!=null;j++){}
                x[customer][j]=y;
                dict.put(customer,x[customer]);
            }
            else 
            {
                
            }
        
        
    }

    void transaction_1(usercan sender,usercan receiver,int s,int r)
    {
        String []y1=new transfer().transfer_(sender,receiver);
        if(y1[0]!="" && y1[1]!="")
        {
            //sender
            for( j=0;x[s][j]!=null;j++){}
            x[s][j]=y1[0];
            dict.put(s,x[s]);
            //receiver
            for( j=0;x[r][j]!=null;j++){}
            x[r][j]=y1[1];
            dict.put(r,x[r]);

        }
    }
}

class withdraw extends trans
{
    Scanner take=new Scanner(System.in);
    String withdraw_(usercan u,int customer)
    {
        System.out.print("Enter Withdrawal Amount : "); 
        int withMoney=take.nextInt();
        if(customer!=1000 && u.RemBal>=withMoney)
        {
            u.RemBal-=withMoney;
            System.out.println("----Withdrawal Complete: Rs."+withMoney+"----");
            DateTimeFormatter formate = DateTimeFormatter.ofPattern("  yyyy/MM/dd : HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String x="Debited Money:"+withMoney+"\t\t\t ON Date : Time - "+((String)formate.format(now));
            
            return x;
        }
        else
        {

            System.out.println("----Balance is less than Rs."+withMoney+"----");return "";
        }

    } 

}

class deposit extends trans
{

    Scanner take=new Scanner(System.in);
    public String deposit_(usercan u,int customer)
   {
        System.out.print("Enter Deposit Amount : "); 
        int deposit=take.nextInt();
        u.RemBal+=deposit;
        System.out.println("----Deposit Complete Rs."+deposit+"----");
        DateTimeFormatter formate = DateTimeFormatter.ofPattern("  yyyy/MM/dd   : HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String x="Credited Money:"+deposit+"\t\t\t On Date : Time - "+((String)formate.format(now));
        return x;    

   }
}

class transfer extends trans
{
    Scanner take=new Scanner(System.in);
    String[] transfer_(usercan s,usercan r)
    {    
        System.out.println("Enter Amount to Transfer: ");
        long m=take.nextLong();
        if(s.RemBal>=m)
        {
            s.RemBal-=m;
            r.RemBal+=m;
            DateTimeFormatter formate = DateTimeFormatter.ofPattern(" yyyy/MM/dd  : HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String x="Sender's Account Number : "+s.Acc+" ,Money : "+m+"\t\t\t On Date : Time -"+((String)formate.format(now));
            String x1="Recepient's Account Number : "+r.Acc+" ,Money : "+m+"\t\t\t On Date : Time -"+((String)formate.format(now));
            System.out.println("--------Money Transferred--------");
            String x2[]={x,x1};
            return x2;
        }
        else{System.out.println("\n-------------Insufficient Funds-------------\n");String y2[]={"",""};return y2;}

    }
    


}
