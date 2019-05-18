package tomaszszewczyk.lab6;

class lab6 {
    public static void main(String[] args) throws InterruptedException {
    
    }
}

class List {
    private Object data;
    private List next;
    private Lock lock = new ReentrantLock();

    public List(Object o) {
        data = o;
    }

    public List(Object o, List n) {
        data = o;
        next = n;
    }

    public Object get() {
        return data;
    }

    public List getNext() {
        return next;
    }

    public boolean contains(Object o) {
        boolean result;
        lock.lock();
        try {
            if(o == data) {
                result = true;
            } else if (next != null) {
                result = next.contains(o);
            } else {
                result = false;
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public List remove(Object o) {
        List result;
        lock.lock();
        try {
            if(o == data) {
                result = next;
            } else if (o == next.get()) {
                next = next.getNext();
                result = this;
            } else {
                next.remove(o);
                result = this;
            }
        } finally {
            lock.unlock();
        }

        return result;
    }

    public void add(Object o) {
        lock.lock();
        try {
            List old_next = next;
            next= new List(o, old_next);
        } finally {
            lock.unlock();
        }
    }

}