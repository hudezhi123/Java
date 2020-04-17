package Test;

import java.util.ArrayList;

public class Test_DataStruct {
    public static ArrayList pickSame(ArrayList arrayA,ArrayList arrayB){
        ArrayList sameList = new ArrayList();
        int indexA =0;
        int indexB =0;
        int index = indexA;
        boolean isChangeSearch = true;
        int judge =1; // 1在 A中查找  -1在B中查找
        while(index<arrayA.size()&&index<=arrayB.size()){
            if(isChangeSearch){
                indexB = searchInB(arrayB,arrayA.get(index));
                isChangeSearch=indexB!=-1;
                if(isChangeSearch){
                    sameList.add(arrayA.get(index));
                    index=++indexB;
                }else{
                    index=++indexA;
                }
            }else{
                indexA= searchInA(arrayA,arrayB.get(index));
                isChangeSearch=indexA!=-1;
                if(isChangeSearch){
                    sameList.add(arrayB.get(index));
                    index=++indexA;
                }else{
                    index=++indexB;
                }
            }
        }
        return sameList;
    }


    /**
     *
     * @param arrayParam
     * @param element
     * @return
     */
    public static int searchIn(ArrayList arrayParam,Object element){
        boolean flag = true;
        int index =1;
        return flag?1:0;
    }

    /**
     *
     * @param arrayParam
     * @param element
     * @return
     */
    public static int searchInA(ArrayList arrayParam,Object element){
        return searchIn(arrayParam,element);
    }


    /**
     *
     * @param arrayParam
     * @param element
     * @return
     */
    public static int searchInB(ArrayList arrayParam,Object element){
        return searchIn(arrayParam,element);
    }




}
