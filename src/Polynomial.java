public class Polynomial {
    
    DList<Term> data = null;
    
    public Polynomial(String s) {
       data = new DList<>();
       s = s.trim();//clean up white space on ends
       s = s.replaceAll("- ", "-");//move any minus operators directly next to term to use as negative sign       
       s = s.replaceAll("\\+", "");//get rid of + operators to cleanly split by spaces
       s = s.replaceAll("  ", " ");//make any instances of two consecutive spaces created by replace's to only one 
       String[] bySpaces = s.split(" ");//Split by spaces to create separate terms
       for(int i = 0; i < bySpaces.length; i++){//loop runs through the length of each array where each element will be a term
           Term termHolder = new Term();//termHolder will hold each term until it is added to the polynomial, then hold the next one
           
           if(bySpaces[i].startsWith("X"))//if the term starts with an X, the coefficient must be a 1.0
               termHolder.setCoefficient(1.0);
           else if(bySpaces[i].startsWith("-X"))//
               termHolder.setCoefficient(-1.0);
           else if (bySpaces[i].contains("X"))
               termHolder.setCoefficient(Double.parseDouble(bySpaces[i].substring(0, bySpaces[i].indexOf("X"))));
           else termHolder.setCoefficient(Double.parseDouble(bySpaces[i].substring(0)));
           
           if(bySpaces[i].contains("X")){
               if(bySpaces[i].contains("^"))
                   termHolder.setDegree(Integer.parseInt(bySpaces[i].substring(bySpaces[i].indexOf("^")+1)));
               else termHolder.setDegree(1);
           }
           else termHolder.setDegree(0);
           data.addLast(termHolder);
       }
   }
    
    public Polynomial() {
       data = new DList<>();
   }
    
    public Polynomial add(Polynomial p) {
      Polynomial ans = new Polynomial();
      DNode<Term> left = null;
      DNode<Term> right = null;
    try {
        left = this.data.getFirst();
        right = p.data.getFirst();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
      while(left.getNext() != null || right.getNext()!= null){
          while(left.getNext() != null && right.getNext()!= null){
              if(left.getData().degree > right.getData().degree){
                  ans.data.addLast(left.getData());
                  left = left.getNext();
                  break;
              }
              if(right.getData().degree > left.getData().degree){
                  ans.data.addLast(right.getData());
                  right = right.getNext();
                  break;
              }
              if(left.getData().degree == right.getData().degree){
                  Term likeTerms = new Term(left.getData().coefficient + right.getData().coefficient, left.getData().degree);
                  ans.data.addLast(likeTerms);
                  left = left.getNext();
                  right = right.getNext();
                  break;
              }
          }
          if(left.getNext() == null){
              while(right.getNext() != null){
                  ans.data.addLast(right.getData());
                  right = right.getNext();
              }
          }
          if(right.getNext() == null){
              while(left.getNext() != null){
                  ans.data.addLast(left.getData());
                  left = left.getNext();
              }
          }
      }
      return ans;
   }

   public Polynomial subtract(Polynomial p){
      Polynomial ans = new Polynomial();
      DNode<Term> right = null;
    try {
        right = p.data.getFirst();
    } catch (Exception e) {
        e.printStackTrace();
    }
      Term termHolder;
      while(right.getNext() != null){
          termHolder = new Term(right.getData().coefficient *-1,right.getData().degree );
          ans.data.addLast(termHolder);
          right = right.getNext();
      }
      ans = this.add(ans);
      return ans;
   }

   public Polynomial multiply(Polynomial p){
      Polynomial ans = new Polynomial("0");
      DNode<Term> left = null;
    try {
        left = this.data.getFirst();
    } catch (Exception e) {
        e.printStackTrace();
    }
      DNode<Term> right = null;
      while(left.getNext() != null){
          Polynomial answerHolder = new Polynomial();
          try {
            right = p.data.getFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
          while(right.getNext() != null){
              Term termHolder = new Term(left.getData().coefficient * right.getData().coefficient, left.getData().degree + right.getData().degree);
              answerHolder.data.addLast(termHolder);
              right = right.getNext();
          }
          ans = ans.add(answerHolder);
          left = left.getNext();
      }
      return ans;
   }

   public Polynomial divide(Polynomial p) throws Exception {
      Polynomial ans = new Polynomial();
      
      return ans;
   }

   public Polynomial remainder(Polynomial p) throws Exception {
      Polynomial ans = new Polynomial();
      // complete this code
      return ans;
   }
   
   public final String toString() {
       String ans = "";
       boolean starting = true;
       try {
          DNode<Term> n = data.getFirst();
          while (n != null) {
             if (!starting && n.getData().isPositive()) ans += " +";
             starting = false;
             ans += " " + n.getData().toString();
             n = data.getNext(n);
          }
       } catch (Exception e) {
          if (starting) return "0";
       }
       return ans;
    }
   
}