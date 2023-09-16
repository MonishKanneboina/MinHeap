public class MinHeap
{
   private Integer[] heap;
   private int size;
   private static final int DEFAULT_CAPACITY = 8;
   
   public MinHeap()  
   {
      heap = new Integer[DEFAULT_CAPACITY +1];
      size = 0; 
   }
   
   public MinHeap(int capacity)
   {
      heap = new Integer[capacity];
      size = 0;
   }
   
   
   
   
   
   
   public MinHeap(Integer... nums)
   {
      heap = new Integer[nums.length+1];
      buildHeap(nums);
   }
   
   
   
    private void buildHeapSiftDown(int index)
    {
      int indexOfSwap = 0;
      int temp = 0;
      if (getLeftChildIndex(index) > size || getRightChildIndex(index) > size)
         return;
      if (heap[index] > heap[getLeftChildIndex(index)] || heap[index] > heap[getRightChildIndex(index)])
      {
         if (heap[getLeftChildIndex(index)] <= heap[getRightChildIndex(index)]){
            indexOfSwap = getLeftChildIndex(index);
            temp = heap[getLeftChildIndex(index)];
         }
         else
         {
            indexOfSwap = getRightChildIndex(index);
            temp = heap[getRightChildIndex(index)];
         }
         heap[indexOfSwap] = heap[index];
         heap[index] = temp;
      }
      else
         return;
      buildHeapSiftDown(indexOfSwap);
   }
   
   
   
   public void buildHeap(Integer[] nums)
   {
      for (int i = 0; i < nums.length; i++)
      {
         heap[size + 1] = nums[i];
         size++;
      }
      for (int i = size/2; i > 0; i--)
      {
         buildHeapSiftDown(i);
      }
   }
   
   
   
   public int getSize()
   {
      return size;
   }
   
   boolean isEmpty()
   {
      if (getSize() == 0)
         return true;
      else
         return false;   
   } 
   
   public int peekMinimum()
   {
      return heap[1];  
   }
   
   public int getLeftChildIndex(int index)
   {
      return index * 2;
   }
   
   public int getRightChildIndex(int index)
   {
      return index * 2 + 1;
   }
   
   public int getParentIndex(int index)
   {
      return index/2;
   }
   
   private void doubleCapacity()
   {
      Integer[] temp = new Integer[heap.length * 2];
      for (int i = 0; i < heap.length - 1; i++)
      {
         temp[i+1] = heap[i+1];
      }
      heap = temp;
   }
   
   public void insert(int value)
   {
      if(heap.length <= getSize() + 1)
         {
            doubleCapacity();
         }  
      heap[size + 1] = value;
      bubbleUp(size + 1);
      size++;
   }
   
   private void bubbleUp(int index)
   {
      if(index == 0 || index == 1)
         return;
      
      if(heap[index] >= heap[getParentIndex(index)])
      {
         return;
      }
      else 
      {
         int temp = heap[getParentIndex(index)];
         heap[getParentIndex(index)] = heap[index];
         heap[index] = temp;
         bubbleUp(getParentIndex(index));
      }
   }
   
   public int popMinimum()
      {
      int min = heap[1];
      size--;
      siftDown(1);
      return min;
      }
   
   private void siftDown(int index)
   {
      int indexOfSwap = 0;
      int temp = 0;
      heap[index] = heap[size+1];
      if (getLeftChildIndex(index) > size)
         return;
      if (getRightChildIndex(index) > size){
         if (heap[index] > heap[getLeftChildIndex(index)]){
            indexOfSwap = getLeftChildIndex(index);
            temp = heap[getLeftChildIndex(index)];
            heap[indexOfSwap] = heap[index];
            heap[index] = temp;
         }
      }
      if (heap[index] > heap[getLeftChildIndex(index)] || heap[index] > heap[getRightChildIndex(index)]){
         if (heap[getLeftChildIndex(index)] <= heap[getRightChildIndex(index)]){
            indexOfSwap = getLeftChildIndex(index);
            temp = heap[getLeftChildIndex(index)];
         }
         else{
            indexOfSwap = getRightChildIndex(index);
            temp = heap[getRightChildIndex(index)];
         }
         heap[indexOfSwap] = heap[index];
         heap[index] = temp;
      }
      else
         return;
      siftDown(indexOfSwap);
        }   
   
   @Override
   public String toString()
   {
      String output = "";
   
      for (int i = 1; i <= getSize(); i++) 
         output += heap[i] + ", ";
   
      if (output.indexOf(",") > -1)   
         return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
      return output;
   
   }

	/** method borrowed with minor modifications from the internet somewhere, for printing */
   public void display() {
      int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
      String dots = "...............................";
      System.out.println(dots + dots);      
      while (j <= this.getSize())
      {
         if (column == 0)                 
            for (int k = 0; k < nBlanks; k++)
               System.out.print(' ');
      
         System.out.print((heap[j] == null)? "" : heap[j]);
      	
         if (++column == itemsPerRow) {
            nBlanks /= 2;                 
            itemsPerRow *= 2;             
            column = 0;                   
            System.out.println();         
         }
         else                             
            for (int k = 0; k < nBlanks * 2 - 2; k++)
               System.out.print(' ');
      	
         j++;
      }
      System.out.println("\n" + dots + dots); 
   }	 
}