import React from 'react';
import { ElevoraLogo } from './ElevoraLogo';

interface NavbarProps {
  onLoginClick: () => void;
  onGetStartedClick: () => void;
}

export const Navbar: React.FC<NavbarProps> = ({ onLoginClick, onGetStartedClick }) => {
  return (
    <header className="sticky top-0 z-50 bg-[#080C16]/90 backdrop-blur-md border-b border-slate-800/80">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-20 flex items-center justify-between">
        
        {/* Exact Elevora Logo */}
        <a href="#hero" className="flex items-center gap-3 group">
          <ElevoraLogo size="md" />
        </a>

        {/* Desktop Nav Links */}
        <nav className="hidden md:flex items-center gap-8 text-sm font-medium text-slate-300">
          <a href="#hero" className="hover:text-amber-400 transition-colors">Home</a>
          <a href="#courses" className="hover:text-amber-400 transition-colors flex items-center gap-1">
            Courses
            <svg className="w-3.5 h-3.5 opacity-60" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 9l-7 7-7-7" />
            </svg>
          </a>
          <a href="#features" className="hover:text-amber-400 transition-colors">Features</a>
          <a href="#business" className="hover:text-amber-400 transition-colors">For Businesses</a>
          <a href="#pricing" className="hover:text-amber-400 transition-colors">Pricing</a>
          <a href="#testimonials" className="hover:text-amber-400 transition-colors">Reviews</a>
          <a href="#blog" className="hover:text-amber-400 transition-colors">Blog</a>
        </nav>

        {/* CTA Buttons */}
        <div className="flex items-center gap-3">
          <button
            onClick={onLoginClick}
            className="px-4 py-2 text-sm font-semibold text-slate-200 hover:text-white bg-slate-900/60 hover:bg-slate-800 border border-slate-700/60 rounded-xl transition-all"
          >
            Login
          </button>
          <button
            onClick={onGetStartedClick}
            className="px-5 py-2.5 text-sm font-bold text-slate-950 bg-gradient-to-r from-amber-400 via-yellow-400 to-amber-500 hover:from-amber-300 hover:to-amber-400 rounded-xl shadow-lg shadow-amber-500/25 hover:shadow-amber-500/40 hover:-translate-y-0.5 transition-all flex items-center gap-1.5"
          >
            Get Started
            <svg className="w-4 h-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2.5} d="M14 5l7 7m0 0l-7 7m7-7H3" />
            </svg>
          </button>
        </div>
      </div>
    </header>
  );
};
