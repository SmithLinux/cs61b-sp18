public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private double G = 6.67e-11;


    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        double xxDistance = this.xxPos - p.xxPos;
        double yyDistance = this.yyPos - p.yyPos;
        double distance = Math.sqrt(xxDistance * xxDistance + yyDistance * yyDistance);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
        double force = (G * this.mass * p.mass) / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double forceX = force * dx / distance;
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = this.yyPos - p.yyPos;
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        double forceY = force * dy / distance;
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double fNetX = 0;
        for (int i = 0; i < p.length; i++) {
            if (!this.equals(p[i])) {
                fNetX += this.calcForceExertedByX(p[i]);
            }   
        }
        return fNetX;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double fNetY = 0;
        for (int i = 0; i < p.length; i++) {
            if (!this.equals(p[i])) {
                fNetY += this.calcForceExertedByY(p[i]);
            }
        }
        return fNetY;
    }

    public void update(double seconds, double xF, double yF) {
        double aX = xF / this.mass;
        double aY = yF / this.mass;
        this.xxVel = xxVel + aX * seconds;
        this.yyVel = yyVel + aY * seconds;
        this.xxPos = xxPos + xxVel * seconds;
        this.yyPos = yyPos + yyVel * seconds;
    }

    /**
    Given a file name, it should return a double corresponding to the radius of the universe in that file, e.g. readRadius("./data/planets.txt") should return 2.50e+11.
     */

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "/images/" + this.imgFileName);
    }

}
