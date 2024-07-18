clear all
close all

Ix = [0,1]; % Def. in x-Richtung
Iy = [1,3]; % Def. in y-Richtung

DIM = [50 50]; % Aufloesung

f = @(x,y) x.^2+x.*sin(y);

%%

[xx,yy] = meshgrid(linspace(Ix(1),Ix(2),DIM(1)),...
    linspace(Iy(1),Iy(2),DIM(2)));
zz = zeros(size(f(xx,yy)));

%%
h=figure(1);

surf(xx,yy,f(xx,yy));
hold on
xlabel("x");
ylabel("y");
zlabel("f(x,y)");
print(h,'-dpng','meinbild.png')

