rm(list=ls())
attach(mtcars)
plot(wt,mpg)
detach(mtcars)

dose <- c(20,30,40,45,60)
drugA <- c(16,20,27,40,60)
drugB <- c(15,18,25,31,40)

drugdata <- data.frame(dose, drugA, drugB)
rm(dose, drugA, drugB)

attach(drugdata)
plot(dose, drugA)
plot(dose, drugA, type = "b")
plot(dose, drugA, type = "b", lty = 2, pch = 8)
plot(dose, drugB, lty = 2, type = "o", col = "purple", pch = 17, cex = 2, lwd = 3)

o.par <- par(no.readonly = T)
par(lwd = 2, cex = 1.5)
plot(dose, drugA, type = 'b', pch = 19, lty = 2, col = "red")
plot(dose, drugB, type = 'b', pch = 15, lty = 3, col = "blue")
par(o.par)

plot(dose, drugA, type = 'b', pch = 19, lty = 2,
     col = "red", lwd = 2, main = "Clinical Trials", xlab = "Dosage",
     ylab = "Drug Response", ylim = c(0,70))
lines(dose, drugB, type = "b", pch = 15, lty = 3, lwd = 2, col = "blue")
legend("topleft", inset = .05, title = "Drug Type", c("A", "B"), lty = c(2,3), pch = c(19, 15),
       col = c("red", "blue"))
