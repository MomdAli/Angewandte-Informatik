clear all
close all

I = [0,2*pi];
DIM = 100;
x = linspace(I(1),I(2),DIM); 
u = sin(x) + (rand(1,DIM)+0.5)/5;
LOOP = 1; delta = 0.1;

% P = load("GlockeR.dat");
% x = P(:,1)'; u = P(:,2)';
% LOOP = 1000; delta = 0.0001;

h = (x(end)-x(1))/(length(x)-1);
su = Smooth(u,delta,LOOP,[1,-2,1]/h^2);
uorg = u;
u = su;

% Jetzt Extrema und Wendepunkte von su bestimmen ...
% Ableitungen berechnen
du  = diskdiff(u,[-1,0,1]/h/2,0);
d2u = diskdiff(u,[1,-2,1]/h^2,1);

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
plot(x,uorg,'o','LineWidth',3)
% plot(x,su,'o','LineWidth',3)
% plot(x,du,'o','LineWidth',3)
% plot(x,d2u,'o','LineWidth',3)
% plot(xE,uE,'o','LineWidth',12)
% plot(xW,uW,'o','LineWidth',12)
legend("u","su","Location","southeast")
grid on
set(gca,'XAxisLocation','origin','YAxisLocation','origin')
print(h1,'-dpng','Ergebnisbild.png');


%% u_neu = u_alt + delta*d2u_alt mehrfach angewendet
function su=Smooth(u,delta,LOOP,F)

su = u;
for l=1:LOOP
    d2u = diskdiff(su,F,1);
    su = su + delta*d2u;
end

end

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
        kk = min(max(1,j+k),N);
        uu(k+1+LL) = u(kk);
    end
    % Faltung des u-Ausschnitts mit dem Filter
    du(j) = sum(uu.*F); 
end

if flag
    du(1) = 0;
    du(end) = 0;
end

end