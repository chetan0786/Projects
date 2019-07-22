import java.util.*;
import java.io.IOException;

class Linkedstack
{
	public class Node { 
  
        int cx;
		int cy;
		int version;
		char shape;
		Node next;
    } 
	public Node top;
	Linkedstack()
	{
		this.top=null;
	}
	public void push(int x,int y,int ver,char s)
	{
		Node temp=new Node();
		temp.cx=x;
		temp.cy=y;
		temp.version=ver;
		temp.shape=s;
		temp.next=top;
		
		top=temp;
		
	}
	
	public void pop()
	{
		top=top.next;
		
	}
	public void empty()
	{
		this.top=null;
	}
	public void peek()
	{
		
		
	}
	public void display()
	{
		System.out.println(top.cx);
		System.out.println(top.cy);
		System.out.println(top.version);
		
	}
	
	
	
}


class Board
{
	public char board[][]=new char[20][20];
	public int del[]=new int [20];
	public Linkedstack utha=new Linkedstack();
public Linkedstack gira=new Linkedstack();
public Stack<Integer> stack = new Stack<Integer>();

public Stack<Integer> redo = new Stack<Integer>();
	public void init()
	{
		for(int i=0;i<20;i++)
		{
			for(int j=0;j<20;j++)
			{
				if(i==0||j==0||i==19||j==19)
				board[i][j]='.';
				else
				board[i][j]=' ';
			}
		}
		
	}
	public void print()
	{
	  for(int i=0;i<20;i++)
	  {
	     for(int j=0;j<20;j++)
	     {
		System.out.print(board[i][j]);
             }	
		System.out.println();
	  }
	}
	
	

}

class Shape
{
public int x=2;
public int y=2;
public int version=1;
public int arr[][]=new int[4][2];


public void fill(Board b)
{
    for(int i=0;i<4;i++)
	{
	b.board[arr[i][0]][arr[i][1]]='#';
	}

}



public void checker(Board b)
{


	for(int i=0;i<4;i++)
	{
		b.del[arr[i][0]]++;
		
		if(b.del[arr[i][0]]==18)
		{
			b.utha.empty();
			b.gira.empty();
			b.stack.clear();
			b.redo.clear();
			
			for(int j=1;j<20;j++)
			{
				b.board[arr[i][0]][j]=' ';
			}
			
			b.del[arr[i][0]]=0;
			int flag=0;
			for(int j=arr[i][0];j>=2;j--)
			{
				flag=0;
				for(int k=1;k<19;k++)
				{
					if(b.board[j-1][k]!=' ')
					{
					b.board[j][k]=b.board[j-1][k];
					b.board[j-1][k]=' ';
					flag=1;
					}
					
				}
				b.del[j]=b.del[j-1];
				b.del[j-1]=0;
				
				
				
			}
			
			
		}
	}
	
}

public void lmove()
{
 int flag=0;
 for(int i=0;i<4;i++)
  {
     if(arr[i][1]>1)
	flag++;
  }
  if(flag==4)
   y--;
}

public void rmove()
{
int flag=0;
 for(int i=0;i<4;i++)
  {
     if(arr[i][1]<18)
	flag++;
  }
  if(flag==4)
   y++;


}
public  boolean notInObj(int s,int d)
    {
        boolean flag=true;
        for(int i=0;i<4;i++)
        {
          if(arr[i][0]==s && arr[i][1]==d) 
             {
               flag=false;
               break;
               }
        } 
        return flag;
    }
public void dmove()
{
	int flag=0;
 for(int i=0;i<4;i++)
  {
     if(arr[i][0]<=19)
	flag++;
  }
  if(flag==4)
   x++;
	
}

public void umove()
{
	int flag=0;
 for(int i=0;i<4;i++)
  {
     if(arr[i][0]>2)
	flag++;
  }
  if(flag==4)
   x--;
	
}

public void vapis(Board b)
{
	if(b.gira.top.shape=='l')
	{
		Line l=new Line();
		


	b.gira.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
				
			}
	}
	else if(b.gira.top.shape=='S')
	{
		Square l=new Square();
	

	b.gira.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	
	else if(b.gira.top.shape=='t')
	{
		Tshape l=new Tshape();


	b.gira.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	else if(b.gira.top.shape=='z')
	{
		Zshape l=new Zshape();
		
	b.gira.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	else if(b.gira.top.shape=='L')
	{
		Lshape l=new Lshape();
		
	b.gira.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	
	
	
}

public void undo(Board b)
{
	
	if(b.utha.top.shape=='l')
	{
		Line l=new Line();
		l.x=b.utha.top.cx;
l.y=b.utha.top.cy;
l.version=b.utha.top.version;

     l.setcoods();
	 l.sidearr(b);
	 
	b.utha.pop();
	
	        try
			{

            l.work(b) ;
			}
			catch(Exception e)
			{
				
			}
	}
	else if(b.utha.top.shape=='S')
	{
		Square l=new Square();
		l.x=b.utha.top.cx;
l.y=b.utha.top.cy;
l.version=b.utha.top.version;
 l.setcoods();
	 l.sidearr(b);

	b.utha.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	
	else if(b.utha.top.shape=='t')
	{
		Tshape l=new Tshape();
		l.x=b.utha.top.cx;
l.y=b.utha.top.cy;
l.version=b.utha.top.version;


 l.setcoods();
	 l.sidearr(b);

	b.utha.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	else if(b.utha.top.shape=='z')
	{
		Zshape l=new Zshape();
		l.x=b.utha.top.cx;
l.y=b.utha.top.cy;
l.version=b.utha.top.version;
 l.setcoods();
	 l.sidearr(b);

	b.utha.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}
	else if(b.utha.top.shape=='L')
	{
		Lshape l=new Lshape();
		l.x=b.utha.top.cx;
l.y=b.utha.top.cy;
l.version=b.utha.top.version;
 l.setcoods();
	 l.sidearr(b);
	
	b.utha.pop();
	
	        try
			{
            l.work(b) ;
			}
			catch(Exception e)
			{
			}
	}

	

	
	
	
}


public void sidearr(Board b)
{
		for(int i=0;i<4;i++)
		{
			if(arr[i][0]>0)
			b.del[arr[i][0]]--;
			
		}
		
	
}




}

class Line extends Shape
{
	
	
public void setcoods()
{
	if(version==1||version==3)
	{
	for(int i=0;i<4;i++)
	{
		arr[i][0]=x+i;
		arr[i][1]=y;
		
	}
	}
	else
	{
	
	for(int i=0;i<4;i++)
	{
		arr[i][0]=x;
		arr[i][1]=y+i;
		
	}
	
	}
       



}



public void remove(Board b)
{
	for(int i=0;i<4;i++)
	{
		
			b.board[arr[i][0]][arr[i][1]]=' ';
		
	}
	
	
}

public void work(Board b) throws IOException, InterruptedException
{
	Scanner sc=new Scanner(System.in);
	setcoods();
	int check=0;
	int count=0;
	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
	super.fill(b);
	b.print();
	boolean enter=true;
	while(enter || (b.board[arr[0][0]+1][arr[0][1]]!='.'&&b.board[arr[1][0]+1][arr[1][1]]!='.'&&b.board[arr[2][0]+1][arr[2][1]]!='.'&&b.board[arr[3][0]+1][arr[3][1]]!='.'))
	{   
		count++;
        if(enter==false)
		{
		int flag=0;
		for(int i=0;i<4;i++)
		{
			if(b.board[arr[i][0]+1][arr[i][1]]=='#'&&super.notInObj(arr[i][0]+1,arr[i][1])){
				flag=1;
			break;}
		}
		if(flag==1)
			break;
		}
		enter=false;
		
		check=sc.nextInt();
		if(check==6)
		{
			b.stack.push(6);
			version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==4)
		{
			b.stack.push(4);
		version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==1)
		{
			b.stack.push(1);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==3)
		{
			b.stack.push(3);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==2)
		{
			b.stack.push(2);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
		
		}
		else if(check==9)
		{
			if(b.stack.empty()==true)
			{ System.out.println("noooooooooo");}
				else{
					
					
			if(b.stack.peek()==1)
			{
				b.redo.push(1);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.stack.peek()==3)
			{
				b.redo.push(3);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
				
			}
			else if(b.stack.peek()==5)
			{
				  remove(b);
				 b.redo.push(5);
				 b.gira.push(2,2,version,'l');
				// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
				 b.stack.pop();
				 
				super.undo(b);
					
			}
			
			else if(b.stack.peek()==6)
			{
				b.redo.push(6);
				b.stack.pop();
						version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
				
			}
			else if(b.stack.peek()==4)
			{
				b.redo.push(4);
				b.stack.pop();
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.stack.peek()==2)
			{
				
				b.redo.push(2);
				b.stack.pop();
					remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.umove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			
			
				}
			
		}
		else if(check==7)
		{
			if(b.redo.empty()==true)
			{}
			else
			{
			if(b.redo.peek()==2)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
				b.stack.push(2);
		b.redo.pop();
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==5)
			{
				
				
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
				b.stack.push(5);
				b.utha.push(x,y,version,'l');
				b.redo.pop();
				
				super.vapis(b);
				
			}
			else if(b.redo.peek()==3)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				b.stack.push(3);
		b.redo.pop();
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==1)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
				b.stack.push(1);
			b.redo.pop();
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==6)
			{
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
						b.stack.push(6);
					b.redo.pop();
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.redo.peek()==4)
			{
				version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
						b.stack.push(4);
					b.redo.pop();
					setcoods();
					super.fill(b);
					b.print();
				
			}
			
			
			
		}
		}
		 
		
		
	}
	
	super.checker(b);
	b.stack.push(5);
	b.utha.push(x,y,version,'l');
	if(check==7)
	{
		b.redo.pop();
		super.vapis(b);
		
	}
}


}

class Lshape extends Shape
{
   public void setcoods()
   {
        if(version==4)
        {    
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x+1;
        arr[1][1]=y;
        arr[2][0]=x+2;
        arr[2][1]=y;
        arr[3][0]=x+2;
        arr[3][1]=y+1;
        }
        else if(version==3)
        {
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x;
        arr[1][1]=y+1;
        arr[2][0]=x;
        arr[2][1]=y+2;
        arr[3][0]=x-1;
        arr[3][1]=y+2;
        }
        else if(version==2)
        {
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x;
        arr[1][1]=y+1;
        arr[2][0]=x+1;
        arr[2][1]=y+1;
        arr[3][0]=x+2;
        arr[3][1]=y+1;  
        }
        else if(version==1)
        {
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x;
        arr[1][1]=y+1;
        arr[2][0]=x;
        arr[2][1]=y+2;
        arr[3][0]=x+1;
        arr[3][1]=y; 
        }	
}
public void remove(Board b)
{
	for(int i=0;i<4;i++)
	{
		
			b.board[arr[i][0]][arr[i][1]]=' ';
		
	}
	
	
}
public void work(Board b) throws IOException, InterruptedException
{
	Scanner sc=new Scanner(System.in);
	setcoods();
	int check=0;
	int count=0;
	 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
	super.fill(b);
	b.print();
	boolean enter=true;
	while(enter || (b.board[arr[0][0]+1][arr[0][1]]!='.'&&b.board[arr[1][0]+1][arr[1][1]]!='.'&&b.board[arr[2][0]+1][arr[2][1]]!='.'&&b.board[arr[3][0]+1][arr[3][1]]!='.'))
	{    count++;
		if(enter==false)
		{
		int flag=0;
		for(int i=0;i<4;i++)
		{
			if(b.board[arr[i][0]+1][arr[i][1]]=='#'&&super.notInObj(arr[i][0]+1,arr[i][1])){
				flag=1;
			break;}
		}
		if(flag==1)
			break;
		}
		enter=false;
		check=sc.nextInt();
		if(check==6)
		{
			b.stack.push(6);
			version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==4)
		{
			b.stack.push(4);
		version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==1)
		{
			b.stack.push(1);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==3)
		{
			b.stack.push(3);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==2)
		{
			b.stack.push(2);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
		
		}
		else if(check==9)
		{
			if(b.stack.empty()==true)
			{ System.out.println("noooooooooo");}
				else{
			if(b.stack.peek()==1)
			{
				b.redo.push(1);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.stack.peek()==3)
			{
				b.redo.push(3);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
				
			}
			else if(b.stack.peek()==5)
			{
				remove(b);
				 				 b.redo.push(5);
				 b.gira.push(2,2,version,'L');
				 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
				 b.stack.pop();
				super.undo(b);
					
			}
			
			else if(b.stack.peek()==6)
			{
				b.redo.push(6);
				b.stack.pop();
						version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
				
			}
			else if(b.stack.peek()==4)
			{
				b.redo.push(4);
				b.stack.pop();
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.stack.peek()==2)
			{
				b.redo.push(2);
				b.stack.pop();
					remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.umove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			
			
				}
			
		}
		else if(check==7)
		{
			if(b.redo.empty()==true)
			{}
			else
			{
			if(b.redo.peek()==2)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(2);
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==5)
			{
								
				
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();		
				b.stack.push(5);
				b.utha.push(x,y,version,'L');
				b.redo.pop();
				
				super.vapis(b);
				
			}
			else if(b.redo.peek()==3)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
			b.stack.push(3);
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==1)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(1);
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==6)
			{
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(6);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.redo.peek()==4)
			{
				version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(4);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			
			b.redo.pop();
			
		}
		}
		 
		
		
	}
	super.checker(b);
	b.stack.push(5);
	
	b.utha.push(x,y,version,'L');
	if(check==7)
	{
		b.redo.pop();
		super.vapis(b);
		
	}

}

}

class Square extends Shape
{
	public void setcoods()
	{
		
		arr[0][0]=x;
		arr[0][1]=y;
		arr[1][0]=x;
		arr[1][1]=y+1;
		arr[2][0]=x+1;
		arr[2][1]=y;
		arr[3][0]=x+1;
		arr[3][1]=y+1;		
	}
	public void remove(Board b)
{
	for(int i=0;i<4;i++)
	{
		
			b.board[arr[i][0]][arr[i][1]]=' ';
		
	}
	
	
}
public void work(Board b) throws IOException, InterruptedException
{
	Scanner sc=new Scanner(System.in);
	setcoods();
	int check=0;
	int count=0;
	 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
	super.fill(b);
	b.print();
	boolean enter=true;
	while(enter || (b.board[arr[0][0]+1][arr[0][1]]!='.'&&b.board[arr[1][0]+1][arr[1][1]]!='.'&&b.board[arr[2][0]+1][arr[2][1]]!='.'&&b.board[arr[3][0]+1][arr[3][1]]!='.'))
	{	count++;
		if(enter==false)
		{
		int flag=0;
		for(int i=0;i<4;i++)
		{
			if(b.board[arr[i][0]+1][arr[i][1]]=='#'&&super.notInObj(arr[i][0]+1,arr[i][1])){
				flag=1;
			break;}
		}
		if(flag==1)
			break;
		}
		enter=false;
		check=sc.nextInt();
		if(check==6)
		{
			b.stack.push(6);
			version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==4)
		{
			b.stack.push(4);
		version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==1)
		{
			b.stack.push(1);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==3)
		{
			b.stack.push(3);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==2)
		{
			b.stack.push(2);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
		
		}
		else if(check==9)
		{
			if(b.stack.empty()==true)
			{ System.out.println("noooooooooo");}
				else{
			if(b.stack.peek()==1)
			{
				b.redo.push(1);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.stack.peek()==3)
			{
				b.redo.push(3);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
				
			}
			else if(b.stack.peek()==5)
			{
				remove(b);
								 b.redo.push(5);
				 b.gira.push(2,2,version,'S');
				 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
				 b.stack.pop();
				super.undo(b);
					
			}
			
			else if(b.stack.peek()==6)
			{
				b.redo.push(6);
				b.stack.pop();
						version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
				
			}
			else if(b.stack.peek()==4)
			{
				b.redo.push(4);
				b.stack.pop();
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.stack.peek()==2)
			{
				b.redo.push(2);
				b.stack.pop();
					remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.umove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			
			
				}
			
		}
		else if(check==7)
		{
			if(b.redo.empty()==true)
			{}
			else
			{
			if(b.redo.peek()==2)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
			b.stack.push(2);
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==5)
			{
								
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
				b.stack.push(5);
				b.utha.push(x,y,version,'S');
				b.redo.pop();
				
				super.vapis(b);
				
			}
			else if(b.redo.peek()==3)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(3);
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==1)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(1);
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==6)
			{
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(6);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.redo.peek()==4)
			{
				version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(4);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			
			b.redo.pop();
			
		}
		}
		 
		
		
	}
	super.checker(b);
	b.stack.push(5);
	
	b.utha.push(x,y,version,'S');
if(check==7)
	{
		b.redo.pop();
		super.vapis(b);
		
	}
}

}

class Zshape extends Shape
{
	public void setcoods()
	{
		if(version==1||version==3)
		{
		arr[0][0]=x;
		arr[0][1]=y;
		arr[1][0]=x+1;
		arr[1][1]=y;
		arr[2][0]=x+1;
		arr[2][1]=y+1;
		arr[3][0]=x+2;
		arr[3][1]=y+1;		
		}
		else
		{
		 arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x;
        arr[1][1]=y+1;
        arr[2][0]=x-1;
        arr[2][1]=y+1;
        arr[3][0]=x-1;
        arr[3][1]=y+2;

		}
	}
	public void remove(Board b)
{
	for(int i=0;i<4;i++)
	{
		
			b.board[arr[i][0]][arr[i][1]]=' ';
		
	}
	
	
}
public void work(Board b) throws IOException, InterruptedException
{
	Scanner sc=new Scanner(System.in);
	setcoods();
	int check=0;
	int count=0;
	 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
	super.fill(b);
	b.print();
	boolean enter=true;
	while(enter || (b.board[arr[0][0]+1][arr[0][1]]!='.'&&b.board[arr[1][0]+1][arr[1][1]]!='.'&&b.board[arr[2][0]+1][arr[2][1]]!='.'&&b.board[arr[3][0]+1][arr[3][1]]!='.'))
	{  	count++;
		if(enter==false)
		{
		int flag=0;
		for(int i=0;i<4;i++)
		{
			if(b.board[arr[i][0]+1][arr[i][1]]=='#'&&super.notInObj(arr[i][0]+1,arr[i][1])){
				flag=1;
			break;}
		}
		if(flag==1)
			break;
		}
		enter=false;
		check=sc.nextInt();
		if(check==6)
		{
			b.stack.push(6);
			version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==4)
		{
			b.stack.push(4);
		version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==1)
		{
			b.stack.push(1);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==3)
		{
			b.stack.push(3);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==2)
		{
			b.stack.push(2);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
		
		}
		else if(check==9)
		{
			if(b.stack.empty()==true)
			{ System.out.println("noooooooooo");}
				else{
			if(b.stack.peek()==1)
			{
				b.redo.push(1);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.stack.peek()==3)
			{
				b.redo.push(3);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
				
			}
			else if(b.stack.peek()==5)
			{
				remove(b);
								 b.redo.push(5);
				 b.gira.push(2,2,version,'z');
				 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
				 b.stack.pop();
				super.undo(b);
					
			}
			
			else if(b.stack.peek()==6)
			{
				b.redo.push(6);
				b.stack.pop();
						version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
				
			}
			else if(b.stack.peek()==4)
			{
				b.redo.push(4);
				b.stack.pop();
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.stack.peek()==2)
			{
				b.redo.push(2);
				b.stack.pop();
					remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.umove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			
			
				}
			
		}
		else if(check==7)
		{
			if(b.redo.empty()==true)
			{}
			else
			{
			if(b.redo.peek()==2)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(2);
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==5)
			{
								
				
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
				b.stack.push(5);
				b.utha.push(x,y,version,'z');
				b.redo.pop();
				
				super.vapis(b);
				
			}
			else if(b.redo.peek()==3)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(3);
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==1)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(1);
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==6)
			{
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(6);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.redo.peek()==4)
			{
				version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(4);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			
			b.redo.pop();
			
		}
		}
		 
		
		
	}
	super.checker(b);
	b.stack.push(5);
	
	b.utha.push(x,y,version,'z');
if(check==7)
	{
		b.redo.pop();
		super.vapis(b);
		
	}
}

}

class Tshape extends Shape
{
	public void setcoods()
	{
		 if(version==4)
        {
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x;
        arr[1][1]=y+1;
        arr[2][0]=x;
        arr[2][1]=y+2;
        arr[3][0]=x+1;
        arr[3][1]=y+1;
        }
        else if(version==3)
        {
         arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x+1;
        arr[1][1]=y;
        arr[2][0]=x+2;
        arr[2][1]=y;
        arr[3][0]=x+1;
        arr[3][1]=y+1;   
        }
        else if(version==2)
        {
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x;
        arr[1][1]=y+1;
        arr[2][0]=x;
        arr[2][1]=y+2;
        arr[3][0]=x-1;
        arr[3][1]=y+1; 
        }
        else if(version==1)
        {
        arr[0][0]=x;
        arr[0][1]=y;
        arr[1][0]=x+1;
        arr[1][1]=y;
        arr[2][0]=x+2;
        arr[2][1]=y;
        arr[3][0]=x+1;
        arr[3][1]=y-1;   
        }
	}
	public void remove(Board b)
{
	for(int i=0;i<4;i++)
	{
		
			b.board[arr[i][0]][arr[i][1]]=' ';
		
	}
	
	
}
public void work(Board b) throws IOException, InterruptedException
{
	Scanner sc=new Scanner(System.in);
	setcoods();
	int check=0;
	int count=0;
	 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
	super.fill(b);
	b.print();
	boolean enter=true;
	while(enter || (b.board[arr[0][0]+1][arr[0][1]]!='.'&&b.board[arr[1][0]+1][arr[1][1]]!='.'&&b.board[arr[2][0]+1][arr[2][1]]!='.'&&b.board[arr[3][0]+1][arr[3][1]]!='.'))
	{  count++;
		if(enter==false)
		{
		int flag=0;
		for(int i=0;i<4;i++)
		{
			if(b.board[arr[i][0]+1][arr[i][1]]=='#'&&super.notInObj(arr[i][0]+1,arr[i][1])){
				flag=1;
			break;}
		}
		if(flag==1)
			break;
		}
		enter=false;
		check=sc.nextInt();
		if(check==6)
		{
			b.stack.push(6);
			version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==4)
		{
			b.stack.push(4);
		version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();

		}
		else if(check==1)
		{
			b.stack.push(1);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==3)
		{
			b.stack.push(3);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
		}
		else if(check==2)
		{
			b.stack.push(2);
			remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
		
		}
		else if(check==9)
		{
			if(b.stack.empty()==true)
			{ System.out.println("noooooooooo");}
				else{
			if(b.stack.peek()==1)
			{
				b.redo.push(1);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.stack.peek()==3)
			{
				b.redo.push(3);
				b.stack.pop();
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
				
			}
			else if(b.stack.peek()==5)
			{
				remove(b);
								 b.redo.push(5);
				 b.gira.push(2,2,version,'t');
				 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
				 b.stack.pop();
				super.undo(b);
					
			}
			
			else if(b.stack.peek()==6)
			{
				b.redo.push(6);
				b.stack.pop();
						version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
				
			}
			else if(b.stack.peek()==4)
			{
				b.redo.push(4);
				b.stack.pop();
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.stack.peek()==2)
			{
				b.redo.push(2);
				b.stack.pop();
					remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		
		super.umove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			
			
				}
			
		}
		else if(check==7)
		{
			if(b.redo.empty()==true)
			{}
			else
			{
			if(b.redo.peek()==2)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(2);
		super.dmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==5)
			{
								
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();		
				b.stack.push(5);
				b.utha.push(x,y,version,'t');
				b.redo.pop();
				
				super.vapis(b);
				
			}
			else if(b.redo.peek()==3)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(3);
		super.rmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==1)
			{
				remove(b);
			 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
		b.stack.push(1);
		super.lmove();
		setcoods();
		super.fill(b);
		b.print();
				
			}
			else if(b.redo.peek()==6)
			{
				version++;
			if(version>4)
                        version=1;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(6);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			else if(b.redo.peek()==4)
			{
				version--;
			if(version<1)
                        version=4;
					remove(b);
					 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();									
					b.stack.push(4);
					setcoods();
					super.fill(b);
					b.print();
				
			}
			
			b.redo.pop();
			
		}
		}
		 
		
		
	}
	super.checker(b);
	b.stack.push(5);
	
	b.utha.push(x,y,version,'t');
if(check==7)
	{
		b.redo.pop();
		super.vapis(b);
		
	}
}

}





class Tetris2
{


	public static void main(String args[]) 
	{
		Scanner sc=new Scanner(System.in);
		Board b=new Board();
               
		b.init();
		
		int check=1;
		while(true)
		{
		int n =(int)(Math.random()*10)%4;
		
		  switch(n)
        {
          case 0:
          {
            Line line = new Line();
			try
			{
            line.work(b) ;
			}
			catch(Exception e)
			{
			}
            break;
          }
		  
          case 1:
          {
            Square line = new Square();
			try
			{
            line.work(b) ;
			}
			catch(Exception e)
			{
			}
           
            break;
          }
          case 2:
          {
            Lshape line = new Lshape();
           try
			{
            line.work(b) ;
			}
			catch(Exception e)
			{
			}
            break;
          }
		  
          case 3:
          {
            Zshape line = new Zshape();
            try
			{
            line.work(b) ;
			}
			catch(Exception e)
			{
			}
            break;
			
          }
		  
          case 4:
          {
            Tshape line = new Tshape();
          try
			{
            line.work(b) ;
			}
			catch(Exception e)
			{
				
			}
            break;
          }
        }
  


		

		
		
		
		

		
		}


	}




}