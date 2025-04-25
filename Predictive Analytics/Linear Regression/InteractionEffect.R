drug <- read.csv("Drug.csv")
str(drug)
drug$Gender <- as.factor(drug$Gender)

t1 = lm(Anxiety ~ Dose, data = drug)
summary(t1)

t2 = lm(Anxiety ~ Gender, data = drug)
summary(t2)

t3 = lm(Anxiety ~ Gender+Dose, data = drug)
summary(t3)

t4 = lm(Anxiety ~ Gender*Dose, data = drug)
summary(t4)

#The same thing because R includes the parents of child variables
t5 = lm(Anxiety ~ Gender + Dose + Gender*Dose, data = drug)
summary(t5)
