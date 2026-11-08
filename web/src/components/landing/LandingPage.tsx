import React from 'react';
import { Navbar } from './Navbar';
import { HeroSection } from './HeroSection';
import { TrustStatsBar } from './TrustStatsBar';
import { WhyChooseSection } from './WhyChooseSection';
import { CourseDetailsSection } from './CourseDetailsSection';
import { FeaturesGridSection } from './FeaturesGridSection';
import { BusinessBannerSection } from './BusinessBannerSection';
import { PricingSection } from './PricingSection';
import { TestimonialsSection } from './TestimonialsSection';
import { BlogSection } from './BlogSection';
import { CtaBannerSection } from './CtaBannerSection';
import { Footer } from './Footer';

interface LandingPageProps {
  onEnterApp: () => void;
  onLoginClick: () => void;
}

export const LandingPage: React.FC<LandingPageProps> = ({ onEnterApp, onLoginClick }) => {
  const handleWatchDemo = () => {
    // Smooth scroll down to course module showcase
    const element = document.getElementById('courses');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  const handleSelectPlan = (planName: string) => {
    // Directly guide user to start learning with the selected plan context
    onEnterApp();
  };

  return (
    <div className="min-h-screen bg-[#080C16] text-slate-100 flex flex-col selection:bg-amber-500 selection:text-slate-950">
      {/* 1. Header Navigation */}
      <Navbar onLoginClick={onLoginClick} onGetStartedClick={onEnterApp} />

      {/* 2. Hero Section with Real Mockup & Executive Visual */}
      <HeroSection onStartLearning={onEnterApp} onWatchDemo={handleWatchDemo} />

      {/* 3. Stats Trust Bar */}
      <TrustStatsBar />

      {/* 4. Why Choose Elevora */}
      <WhyChooseSection />

      {/* 5. Course Details (Module 1 Week 1 Day 1 & 4-Module Path) */}
      <CourseDetailsSection onStartCourse={onEnterApp} />

      {/* 6. Powerful Learning Features Grid */}
      <FeaturesGridSection />

      {/* 7. Elevora for Businesses */}
      <BusinessBannerSection onContactSales={onEnterApp} />

      {/* 8. Pricing Plans */}
      <PricingSection onSelectPlan={handleSelectPlan} />

      {/* 9. Learner Testimonials */}
      <TestimonialsSection />

      {/* 10. Blog & Insights */}
      <BlogSection />

      {/* 11. Final CTA Banner */}
      <CtaBannerSection onStartLearning={onEnterApp} onWatchDemo={handleWatchDemo} />

      {/* 12. Footer */}
      <Footer />
    </div>
  );
};

