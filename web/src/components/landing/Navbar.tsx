import React from 'react';
import { ElevoraLogo } from './ElevoraLogo';

interface NavbarProps {
  onLoginClick: () => void;
  onGetStartedClick: () => void;
}

export const Navbar: React.FC<NavbarProps> = ({ onLoginClick, onGetStartedClick }) => {
  return (
    <header className="sticky top-0 z-50 bg-[#060D1E]/95 backdrop-blur-md border-b border-white/5">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-20 flex items-center justify-between">
        
        {/* Exact Elevora Logo */}
        <a href="#hero" className="hover:opacity-90 transition-opacity">
          <ElevoraLogo size="md" />
        </a>

        {/* Center Nav Links with active underlined/bold states matching image */}
        <nav className="hidden lg:flex items-center gap-7 text-[13px] font-semibold text-slate-300">
          <a href="#hero" className="text-white hover:text-amber-400 transition-colors">Home</a>
          
          <div className="relative group cursor-pointer flex items-center gap-1 hover:text-amber-400 transition-colors">
            <a href="#courses">Courses</a>
            <svg className="w-3.5 h-3.5 text-slate-400" viewBox="0 0 20 20" fill="currentColor">
              <path fillRule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clipRule="evenodd" />
            </svg>
          </div>

          <a href="#features" className="hover:text-amber-400 transition-colors">Features</a>
          
          {/* For Businesses highlighted */}
          <a href="#business" className="text-white underline decoration-amber-400 decoration-2 underline-offset-8 font-bold">
            For Businesses
          </a>

          <a href="#pricing" className="hover:text-amber-400 transition-colors">Pricing</a>
          <a href="#about" className="hover:text-amber-400 transition-colors">About</a>
        </nav>

        {/* Right CTA Buttons: Oval outlined 'Login' & Pill yellow filled 'Get Started →' */}
        <div className="flex items-center gap-3">
          <button
            onClick={onLoginClick}
            className="px-6 py-2 rounded-full text-xs font-semibold text-white border border-slate-600 hover:border-amber-400 hover:text-amber-400 transition-all"
          >
            Login
          </button>
          
          <button
            onClick={onGetStartedClick}
            className="px-6 py-2.5 rounded-full text-xs font-bold text-slate-950 bg-[#F5A623] hover:bg-[#e0951b] transition-all flex items-center gap-1.5 shadow-md shadow-amber-500/20"
          >
            <span>Get Started</span>
            <span className="text-sm font-black">→</span>
          </button>
        </div>

      </div>
    </header>
  );
};
