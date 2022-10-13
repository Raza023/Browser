import java.util.Stack;

public class pnHandler
{
    Stack<String> bStack;
    Stack<String> fStack;
    
    public pnHandler()
    {
        bStack= new Stack<>();
        fStack= new Stack<>();
    }
    
    public void MoveForward(String str)
    {
        fStack.push(str);
    }
    
    public void MoveBackward(String str)
    {
        bStack.push(str);
    }
    
    public String TopOnForward()
    {
        return fStack.pop();
    }
    
    public String TopOnBackward()
    {
        return bStack.pop();
    }
    
    public boolean isEmptyBackward()
    {
        return bStack.isEmpty();
    }
    
    public boolean isEmptyForward()
    {
        return fStack.isEmpty();
    }
}
