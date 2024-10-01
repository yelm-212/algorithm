class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int[] newWallet = sort(wallet);
        int[] newBill = sort(bill);
        
        while( (newWallet[0] < newBill[0]) || (newWallet[1] < newBill[1])) {
            newBill[1] = newBill[1] / 2;
            newBill = sort(newBill);
            answer++;
        }
        
        return answer;
    }
    
    public int[] sort(int[] arr){
        if (arr[0] > arr[1]){
            int tmp = arr[0];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
        
        return arr;
    }
}