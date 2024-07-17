clear all
close all

zb = 1; % zerobound (zb=1)

% ein kleines Beispiel
x = [1, 2, 3, 4, 5, 6];
u = [1, 4, 3, -1, 2, 3];

% ein groesseres Beispiel
% I = [0,2*pi];
% DIM = 100;
% x = linspace(I(1),I(2),DIM); 
% u = sin(x);

% ein Beispiel mit Daten
% P = load("Glocke.dat");
% x = P(:,1); u = P(:,2);

h = (x(end)-x(1))/(length(x)-1);

% Ableitungen berechnen
du  = diskdiff(u,[-1,0,1]/h/2,0);
d2u = diskdiff(u,[1,-2,1]/h^2,zb);

% Extremwert schaetzen
% IndE = find(du(1:end-1).*du(2:end)<0);
% xE = (x(IndE)+x(IndE+1))/2;
% uE = (u(IndE)+u(IndE+1))/2;

% Wendepunkte schaetzen
% IndW = find(d2u(1:end-1).*d2u(2:end)<0);
% xW = (x(IndW)+x(IndW+1))/2;
% uW = (u(IndW)+u(IndW+1))/2;

%%
h1 = figure(1);
hold on
plot(x,u,'o','LineWidth',3)
plot(x,du,'o','LineWidth',3)
%plot(x,d2u,'o','LineWidth',3)
% plot(xE,uE,'o','LineWidth',12)
% plot(xW,uW,'o','LineWidth',12)
legend("u","du","Location","southeast")
%legend("u","du","E","W","Location","southeast")
%legend("u","du","d2u","E","W","Location","southeast")
grid on
set(gca,'XAxisLocation','origin','YAxisLocation','origin')
print(h1,'-dpng','Ergebnisbild.png');


%%
function du=diskdiff(u,F,flag)

N = length(u);
L=length(F); % sollte ungerade sein!
if mod(L,2)==0; fprintf("Error in MyDiskdiff\n"); pause(10); end
LL=(L-1)/2;
du = zeros(1,N);

for j=1:N
    % u-Ausschnitt in Filtergroesse
    for k=-LL:LL
        kk = min(max(1,j+k),N); % Daten konstant fortsetzen
        uu(k+1+LL) = u(kk);
    end
    % "Faltung" des u-Ausschnitts mit dem Filter
    % oder nennen Sie es "Anwendung des Filters"
    du(j) = sum(uu.*F); 
end

if flag
    du(1) = 0;
    du(end) = 0;
end

end