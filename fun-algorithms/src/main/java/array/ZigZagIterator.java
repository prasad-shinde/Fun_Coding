package array;

import java.util.Iterator;
import java.util.List;

public class ZigZagIterator {
    Iterator<Integer> v1It;
    Iterator<Integer> v2It;
    boolean isV1;
    
    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        v1It = v1.iterator();
        v2It = v2.iterator();
        isV1 = true;
    }

    public int next() {
        if(!hasNext())
            throw new NullPointerException();
        if(isV1) {
            isV1 = !isV1;
            if(v1It.hasNext())
                return v1It.next();
            else
                if(v2It.hasNext())
                    return v2It.next();
            
        } else {
            isV1 = !isV1;
            if(v2It.hasNext())
                return v2It.next();
            else
                if(v1It.hasNext())
                    return v1It.next();
                
        }
        throw new NullPointerException();
    }

    public boolean hasNext() {
        return v1It.hasNext() || v2It.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */