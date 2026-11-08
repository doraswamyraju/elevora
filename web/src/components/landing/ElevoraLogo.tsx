import React from 'react';

interface LogoProps {
  className?: string;
  size?: 'sm' | 'md' | 'lg';
  showSubtitle?: boolean;
}

export const ElevoraLogo: React.FC<LogoProps> = ({ 
  className = '', 
  size = 'md',
  showSubtitle = false 
}) => {
  const heights = {
    sm: 'h-8',
    md: 'h-10 sm:h-11',
    lg: 'h-14'
  };

  return (
    <div className={`flex items-center gap-3 select-none ${className}`}>
      {/* Exact authentic Elevora logo from Android application */}
      <img 
        src="/assets/elevora-logo-banner.png" 
        alt="Elevora" 
        className={`${heights[size]} w-auto object-contain`}
      />
      {showSubtitle && (
        <span className="text-[10px] text-slate-400 font-medium tracking-wide border-l border-slate-700 pl-2.5 hidden sm:inline-block">
          Professional English
        </span>
      )}
    </div>
  );
};
